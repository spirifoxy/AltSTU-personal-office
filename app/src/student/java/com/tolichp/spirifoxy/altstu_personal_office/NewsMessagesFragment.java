package com.tolichp.spirifoxy.altstu_personal_office;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.FeedListAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.app.AppController;
import com.tolichp.spirifoxy.altstu_personal_office.data.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class NewsMessagesFragment extends Fragment {
        private static final String TAG = NewsFragment.class.getSimpleName();
        private ListView listView;
        private FeedListAdapter listAdapter;
        private List<FeedItem> feedItems;
        private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

    @SuppressLint("NewApi")

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_news);
            View view = inflater.inflate(R.layout.fragment_news_messages, null);
            listView = (ListView) view.findViewById(R.id.list);

            feedItems = new ArrayList<>();
            listAdapter = new FeedListAdapter(feedItems);
            listView.setAdapter(listAdapter);

            // We first check for cached request
            Cache cache = AppController.getInstance().getRequestQueue().getCache();
            Entry entry = cache.get(URL_FEED);
            if (entry != null) {
                // fetch the data from cache
                try {
                    String data = new String(entry.data, "UTF-8");
                    try {
                        parseJsonFeed(new JSONObject(data));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else {
                // making fresh volley request and getting json
                JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
                        URL_FEED, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, "Response: " + response.toString());
                        if (response != null) {
                            parseJsonFeed(response);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

                // Adding request to volley request queue
                AppController.getInstance().addToRequestQueue(jsonReq);
            }

//        return (R.layout.fragment_news);
        return view;
        }

        /**
         * Parsing json reponse and passing the data to feed view list com.tolichp.spirifoxy.altstu_personal_office.adapter
         * */
        private void parseJsonFeed(JSONObject response) {
            try {
                JSONArray feedArray = response.getJSONArray("feed");

                for (int i = 0; i < feedArray.length(); i++) {
                    JSONObject feedObj = (JSONObject) feedArray.get(i);

                    FeedItem item = new FeedItem();
                    item.setId(feedObj.getInt("id"));
                    item.setName(feedObj.getString("name"));

                    // Image might be null sometimes
                    String image = feedObj.isNull("image") ? null : feedObj
                            .getString("image");
                    item.setImge(image);
                    item.setStatus(feedObj.getString("status"));
                    item.setProfilePic(feedObj.getString("profilePic"));
                    item.setTimeStamp(feedObj.getString("timeStamp"));

                    // url might be null sometimes
                    String feedUrl = feedObj.isNull("url") ? null : feedObj
                            .getString("url");
                    item.setUrl(feedUrl);

                    feedItems.add(item);
                }

                // notify data changes to list adapater
                listAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }