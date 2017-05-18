package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.SessionsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;

public class SessionsSemFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private SessionsRecyclerAdapter mTSessionsAdapter;

    public static SessionsSemFragment newInstance() {//String param1, String param2) {
        SessionsSemFragment fragment = new SessionsSemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_session_sem, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_sessions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = 0; i< 13; i++) {
            subjects.add(new Subject("test subject " + i ));
        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mTSessionsAdapter = new SessionsRecyclerAdapter(subjects);
        mRecyclerView.setAdapter(mTSessionsAdapter);

        return view;
    }
}
