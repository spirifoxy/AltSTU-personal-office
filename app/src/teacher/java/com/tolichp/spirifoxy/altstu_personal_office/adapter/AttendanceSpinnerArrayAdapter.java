package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by spirifoxy on 22.05.2017.
 */

public class AttendanceSpinnerArrayAdapter extends ArrayAdapter<String> {

    public AttendanceSpinnerArrayAdapter(Context context, int item, ArrayList<String> itemsList) {
        super(context, item, itemsList);
    }

    @Override
    public boolean isEnabled(int position){
        return position != 0;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        TextView tv = (TextView) view;
        int color = position == 0 ? Color.GRAY : Color.BLACK;
        tv.setTextColor(color);

        return view;
    }
}
