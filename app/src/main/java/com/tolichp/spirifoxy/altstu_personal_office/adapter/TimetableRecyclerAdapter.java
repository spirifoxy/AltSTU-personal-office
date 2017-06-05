package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.ViewHolder> {

    private ArrayList<Lesson> lessonsList = new ArrayList<>(); //TODO подумать, где инициализировать

    public TimetableRecyclerAdapter(ArrayList<Lesson> lessonsList) {
        this.lessonsList = lessonsList;

        // T/*ODO ошибка - выше присваивается null*/
        /*this.lessonsList = new ArrayList<>();
        this.lessonsList.add(new Lesson());
        this.lessonsList.add(new Lesson());
        this.lessonsList.add(new Lesson());*/
    }

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
        Lesson lesson = lessonsList.get(position);

        Locale locale = new Locale("ru","RU");
//        SimpleDateFormat format = new SimpleDateFormat("h:mm");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", locale);
        String startTime = format.format(lesson.getStartTime().getTime());
        String endTime = format.format(lesson.getEndTime().getTime());

        holder.mTextViewTitle.setText(lesson.getTitle());
        holder.mTextViewStartTime.setText(startTime);
        holder.mTextViewEndTime.setText(endTime);
        holder.mTextViewTAudience.setText(lesson.getAudience());
        holder.mTextViewClasstype.setText(lesson.getClassType());
        holder.mTextViewInfo.setText(lesson.getInfo());

        if(holder.itemView != null) {
            holder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //holder.itemView.setBackgroundColor(Utils.getRandomColor());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, null));
    }

    @Override
    public int getItemCount() {
        return lessonsList.size();
    }


}
