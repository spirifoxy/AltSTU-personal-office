package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyingStudent;

import java.util.ArrayList;

/**
 * Created by Alexandr on 27.05.2017.
 */
public class StudentsInMarksAdapter extends RecyclerView.Adapter<StudentsInMarksAdapter.ViewHolder> {

    private ArrayList<StudyingStudent> StudyingStudentsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    public StudentsInMarksAdapter(ArrayList<StudyingStudent> StudyingStudentsList) {
        this.StudyingStudentsList = StudyingStudentsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewStudentNumber;
        TextView mTextViewStudentName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewStudentNumber = (TextView) itemView.findViewById(R.id.textview_student_inmarks_number);
            mTextViewStudentName = (TextView) itemView.findViewById(R.id.textview_student_inmarks_fio);
        }
    }

    @Override
    public void onBindViewHolder(StudentsInMarksAdapter.ViewHolder holder, int position) {

        StudyingStudent StudyingStudent = StudyingStudentsList.get(position);
        holder.mTextViewStudentName.setText(StudyingStudent.getFio());
        holder.mTextViewStudentNumber.setText(String.valueOf(StudyingStudent.getNumber()));

        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_in_marks, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return StudyingStudentsList.size();
    }
}
