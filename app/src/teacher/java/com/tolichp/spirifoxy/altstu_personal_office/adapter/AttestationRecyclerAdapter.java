package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.R;
import com.tolichp.spirifoxy.altstu_personal_office.data.AttestatedStudent;

import java.util.ArrayList;

public class AttestationRecyclerAdapter extends RecyclerView.Adapter<AttestationRecyclerAdapter.ViewHolder> {

    private ArrayList<AttestatedStudent> AttestatedStudentsList = new ArrayList<>(); //TODO подумать, где инициализировать
    static final String TAG = "myLogs";

    public AttestationRecyclerAdapter(ArrayList<AttestatedStudent> AttestatedStudentsList) {
        this.AttestatedStudentsList = AttestatedStudentsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView mTextViewAttestatedStudentName;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewAttestatedStudentName = (TextView) itemView.findViewById(R.id.textView_fio);
        }
    }

    @Override
    public void onBindViewHolder(AttestationRecyclerAdapter.ViewHolder holder, int position) {
        AttestatedStudent AttestatedStudent = AttestatedStudentsList.get(position);
        holder.mTextViewAttestatedStudentName.setText(AttestatedStudent.getFIO());

        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "rl click");
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attestated_student, parent, false));//null));
    }

    @Override
    public int getItemCount() {
        return AttestatedStudentsList.size();
    }


}
