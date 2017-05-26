package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.Mark;

import java.util.ArrayList;

/**
 * Created by Alexandr on 27.05.2017.
 */
public class PointsInMarksAdapter extends RecyclerView.Adapter<PointsInMarksAdapter.ViewHolder> {

    private ArrayList<Mark> markArrayList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    public PointsInMarksAdapter(ArrayList<Mark> StudyingStudentsList) {
        this.markArrayList = StudyingStudentsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            }
    }

    @Override
    public void onBindViewHolder(PointsInMarksAdapter.ViewHolder holder, int position) {

        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_points_in_marks, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return markArrayList.size();
    }
}
