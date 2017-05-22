package com.tolichp.spirifoxy.altstu_personal_office;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttestationRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.AttestatedStudent;

import java.util.ArrayList;

public class AttestationListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private AttestationRecyclerAdapter attestationRecyclerAdapter;

    public static AttestationListFragment newInstance() {
        AttestationListFragment fragment = new AttestationListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_attestation_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_attestation);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<AttestatedStudent> students = new ArrayList<>();
        for (int i = 0; i< 13; i++) {
            students.add(new AttestatedStudent(i, "Ivanov Ivan" ));

        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        attestationRecyclerAdapter = new AttestationRecyclerAdapter(students); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(attestationRecyclerAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
