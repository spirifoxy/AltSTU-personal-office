package com.tolichp.spirifoxy.altstu_personal_office.adapter;

/**
 * Created by Alexandr on 23.05.2017.
 */

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
import com.tolichp.spirifoxy.altstu_personal_office.StudyingStudentFragment;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyingStudent;

import java.util.ArrayList;

public class StudyRecyclerAdapter extends RecyclerView.Adapter<StudyRecyclerAdapter.ViewHolder> {

    private ArrayList<StudyingStudent> StudyingStudentsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    private Context context;

    public StudyRecyclerAdapter(Context context, ArrayList<StudyingStudent> StudyingStudentsList) {
        this.context = context;
        this.StudyingStudentsList = StudyingStudentsList;

        // TODO ошибка - выше присваивается null
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewStudyingStudentName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewStudyingStudentName = (TextView) itemView.findViewById(R.id.textview_fio);
        }
    }

    @Override
    public void onBindViewHolder(StudyRecyclerAdapter.ViewHolder holder, int position) {
        StudyingStudent studyingStudent = StudyingStudentsList.get(position);
        holder.mTextViewStudyingStudentName.setText(studyingStudent.getFio());

        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "rl click");
                    Fragment fragment = new StudyingStudentFragment();//.newInstance();
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();

                    transaction.replace(R.id.content_study, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studyingstudent, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return StudyingStudentsList.size();
    }


}
