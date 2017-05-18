package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.app.Activity;
import android.content.Context;
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
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.StudyProgressFragment;
import com.tolichp.spirifoxy.altstu_personal_office.SubjectFragment;
import com.tolichp.spirifoxy.altstu_personal_office.app.AppController;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class StudyProgressRecyclerAdapter extends RecyclerView.Adapter<StudyProgressRecyclerAdapter.ViewHolder> {

    private ArrayList<Subject> subjectsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    private Context context;

    public StudyProgressRecyclerAdapter(Context context, ArrayList<Subject> subjectsList) {
        this.context = context;
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
//            mRelativeLayoutShowmore = (RelativeLayout) itemView.findViewById(R.id.layout_re);
            mTextViewSubjectName = (TextView) itemView.findViewById(R.id.textview_subjectname);
        }
    }

    @Override
    public void onBindViewHolder(StudyProgressRecyclerAdapter.ViewHolder holder, int position) {
        Subject subject = subjectsList.get(position);
        holder.mTextViewSubjectName.setText(subject.getName());



        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "rl click");
                    Fragment fragment = new SubjectFragment();//.newInstance();
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    
                    transaction.replace(R.id.content_studyprogress, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
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
