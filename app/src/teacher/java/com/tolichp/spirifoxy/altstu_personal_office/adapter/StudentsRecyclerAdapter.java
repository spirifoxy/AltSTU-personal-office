package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.Student;

import java.util.ArrayList;

/**
 * Created by spirifoxy on 12.05.2017.
 */

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder> {

    private ArrayList<Student> studentsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    private Context context;

    public StudentsRecyclerAdapter(Context context, ArrayList<Student> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewStudentNumber;
        TextView mTextViewStudentFullName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewStudentNumber = (TextView) itemView.findViewById(R.id.textview_number);
            mTextViewStudentFullName = (TextView) itemView.findViewById(R.id.textview_full_name);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = studentsList.get(position);
        holder.mTextViewStudentNumber.setText((position+1) + ".");
        holder.mTextViewStudentFullName.setText(student.name + " " + student.surname);



        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_attendance, parent, false));
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


}
