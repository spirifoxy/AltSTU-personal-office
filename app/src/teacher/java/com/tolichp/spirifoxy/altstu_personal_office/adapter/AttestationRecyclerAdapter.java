package com.tolichp.spirifoxy.altstu_personal_office.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        TextView mTextViewStudentNumber;
        TextView mTextViewStudentName;
        EditText mEditTextStudentAttestation1;
        EditText mEditTextStudentAttestation2;
        EditText mEditTextStudentAttendance1;
        EditText mEditTextStudentAttendance2;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewStudentNumber = (TextView) itemView.findViewById(R.id.at_textview_number);
            mTextViewStudentName = (TextView) itemView.findViewById(R.id.at_textview_full_name);

            mEditTextStudentAttestation1 = (EditText) itemView.findViewById(R.id.attestation1);
            mEditTextStudentAttestation2 = (EditText) itemView.findViewById(R.id.attestation2);
            mEditTextStudentAttendance1 = (EditText) itemView.findViewById(R.id.atendance1);
            mEditTextStudentAttendance2 = (EditText) itemView.findViewById(R.id.atendance2);
        }
    }

    @Override
    public void onBindViewHolder(AttestationRecyclerAdapter.ViewHolder holder, int position) {

        AttestatedStudent attestatedStudent = AttestatedStudentsList.get(position);
        holder.mTextViewStudentName.setText(attestatedStudent.getFIO());
        holder.mTextViewStudentNumber.setText(String.valueOf(attestatedStudent.getNumber()));
        holder.mEditTextStudentAttestation1.setText(String.valueOf(attestatedStudent.getAttestation1()));
        holder.mEditTextStudentAttestation2.setText(String.valueOf(attestatedStudent.getAttestation2()));
        holder.mEditTextStudentAttendance1.setText(String.valueOf(attestatedStudent.getAttendance1()));
        holder.mEditTextStudentAttendance2.setText(String.valueOf(attestatedStudent.getAttendance2()));

        if(holder.mItemView != null) {
            holder.mItemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
