package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewStartTime;
        TextView mTextViewEndTime;
        TextView mTextViewTitle;
        TextView mTextViewTAudience;
        TextView mTextViewClasstype;
        TextView mTextViewInfo;
        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewStartTime = (TextView) itemView.findViewById(R.id.textview_start_time);
            mTextViewEndTime = (TextView) itemView.findViewById(R.id.textview_end_time);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.textview_title);
            mTextViewTAudience = (TextView) itemView.findViewById(R.id.textview_audience);
            mTextViewClasstype = (TextView) itemView.findViewById(R.id.textview_classtype);
            mTextViewInfo = (TextView) itemView.findViewById(R.id.textview_info);
        }
    }

    @Override
    public void onBindViewHolder(TimetableRecyclerAdapter.ViewHolder holder, int position) {
        //TODO not implemented
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, null));
    }

    @Override
    public int getItemCount() {
        //TODO not implemented
        return 0;
    }


}
