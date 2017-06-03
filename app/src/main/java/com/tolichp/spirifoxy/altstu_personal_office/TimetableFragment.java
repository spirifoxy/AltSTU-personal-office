package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tolichp.spirifoxy.altstu_personal_office.app.AppController;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyGroup;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class TimetableFragment extends Fragment {

    static final String TAG = "myLogs";

    private ScrollerViewPager viewPager;

    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter pagerAdapter; //TODO убрать? мб сразу созданный объект слать куда там надо
    private Spinner mCountrySpinner;

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_WEEK_VIEW = 2;
    private int mWeekViewType = TYPE_DAY_VIEW;

    private String URL_DAYS = "http://altstu-server/web/api/v1/timetables/all";
    private List<Day> days;
    
    public static TimetableFragment newInstance() {//String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        getActivity().setTitle(getResources().getString(R.string.timetable));
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        days = new ArrayList<>();

        // prepare the Request
        DateFormat df = new SimpleDateFormat("y-m-d");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        StudyGroup newGroup = new StudyGroup();
        String url = "/timetable/" + newGroup.getName1() + "/" + newGroup.getName2() + "/week/" +reportDate;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response","error");
                    }
                });
        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_DAYS);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonDays(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_DAYS, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    // We first check for cached request
                    Cache cache = AppController.getInstance().getRequestQueue().getCache();
                    Cache.Entry entry = cache.get(URL_DAYS);
                    if (entry != null) {
                        // fetch the data from cache
                        try {
                            String data = new String(entry.data, "UTF-8");
                            try {
                                parseJsonDays(new JSONObject(data));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    } else {
                        // making fresh volley request and getting json
                        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                                URL_DAYS, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                VolleyLog.d(TAG, "Response: " + response.toString());
                                if (response != null) {
                                    parseJsonDays(response);
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

        
        Fragment fragment = TTDayFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_timetable, fragment);
        transaction.commit();

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.week_menu, menu);
        inflater.inflate(R.menu.datapicker_menu, menu);
        inflater.inflate(R.menu.change_tt_view_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.spinner_week);
        mCountrySpinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.week_numbers));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountrySpinner.setAdapter(countryAdapter);
    }

    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_to_change_icon_for); // You can change the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_datapicker:
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getActivity().getSupportFragmentManager(), "datePicker");
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;

                    Fragment fragment = TTDayFragment.newInstance();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_timetable, fragment);
                    transaction.commit();

                }
                return true;

            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;

                    Fragment fragment = TTWeekFragment.newInstance();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_timetable, fragment);
                    transaction.commit();

                }
                return true;
        }
        return false;
    }


    private int getCurrentWeekNumber() {
        Locale locale = new Locale("ru","RU");
        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        Date currentDate = new Date();
        cal.setTime(currentDate);

        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);

        Date firstSemDate = null;
        Date secondSemDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", locale);
        try {firstSemDate = sdf.parse(getResources().getString(R.string.first_sep) + "-" + year);
            secondSemDate = sdf.parse(getResources().getString(R.string.first_feb) + "-" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar semBegin = GregorianCalendar.getInstance(tz, locale);
        if (currentDate.after(firstSemDate) && currentDate.before(secondSemDate)) { //первый семестр
            semBegin.setTime(firstSemDate);
        } else {
            semBegin.setTime(secondSemDate);
        }
        return week - semBegin.get(Calendar.WEEK_OF_YEAR) + 1;
    }

    /**
     * Parsing json reponse and passing the data to days view list com.tolichp.spirifoxy.altstu_personal_office.adapter
     * */
    private void parseJsonDays(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("days");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject daysObj = (JSONObject) feedArray.get(i);

                Day item = new Day();

               /* item.setId(daysObj.getInt("id"));
                item.setName(daysObj.getString("name"));

                // days might be null sometimes
                List<Day> days = daysObj.isNull("days") ? null : daysObj
                        .getJSONArray("days");
                item.setDays(days);*/

                days.add(item);
            }

            // notify data changes to list adapater
            //listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
