package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class StudyprogressRecyclerAdapter extends RecyclerView.Adapter<StudyprogressRecyclerAdapter.ViewHolder> {

    private ArrayList<Subject> subjectsList = new ArrayList<>(); //TODO подумать, где инициализировать

    public StudyprogressRecyclerAdapter(ArrayList<Subject> subjectsList) {
        this.subjectsList = subjectsList;

        // TODO ошибка - выше присваивается null
        /*this.subjectsList = new ArrayList<>();
        this.subjectsList.add(new Lesson());
        this.subjectsList.add(new Lesson());
        this.subjectsList.add(new Lesson());*/
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewSubjectName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewSubjectName = (TextView) itemView.findViewById(R.id.textview_subjectname);
        }
    }

    @Override
    public void onBindViewHolder(StudyprogressRecyclerAdapter.ViewHolder holder, int position) {
        Subject subject = subjectsList.get(position);
        holder.mTextViewSubjectName.setText(subject.getName());

        if(holder.itemView != null) {
            holder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studyprogress, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }


}
