package com.tolichp.spirifoxy.altstu_personal_office.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tolichp.spirifoxy.altstu_personal_office.app.AppController;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyGroup;
import com.tolichp.spirifoxy.altstu_personal_office.utils.ServerCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by spirifoxy on 04.06.2017.
 */

public class TimetableController {

    public void getTwooWeeksTimetable(final StudyGroup group, final String today, final Context context, final ServerCallback callback)
    {
        final String url = "http://10.0.2.2/api/v1/timetable/" + group.getName1() + "/" + group.getName2() + "/week/" +today;
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()  {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response", "ok");
                callback.onSuccess(response); // call call back function here

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley error json object ", "Error: " + error.getMessage());

            }
        }){
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonReq);

    }
}
