package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.ControlPoint;

import java.util.ArrayList;

/**
 * Created by spirifoxy on 23.05.2017.
 */

public class ControlPointsInfoRecyclerAdapter extends RecyclerView.Adapter<ControlPointsInfoRecyclerAdapter.ViewHolder> {

    private ArrayList<ControlPoint> controlPointsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    private Context context;

    public ControlPointsInfoRecyclerAdapter(Context context, ArrayList<ControlPoint> controlPointsList) {
        this.context = context;
        this.controlPointsList = controlPointsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewSubjectName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
//            mRelativeLayoutShowmore = (RelativeLayout) itemView.findViewById(R.id.layout_re);
            mTextViewSubjectName = (TextView) itemView.findViewById(R.id.textview_controlpoint_info_name);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ControlPoint controlPoint = controlPointsList.get(position);
        holder.mTextViewSubjectName.setText(controlPoint.getName());



        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_controlpoint_info, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return controlPointsList.size();
    }


}
