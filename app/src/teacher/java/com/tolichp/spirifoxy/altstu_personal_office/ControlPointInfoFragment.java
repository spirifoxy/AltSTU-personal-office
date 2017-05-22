package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsInfoRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.ControlPoint;
import com.tolichp.spirifoxy.altstu_personal_office.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlPointInfoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ControlPointsInfoRecyclerAdapter mControlPointsInfoAdapter;

    public static ControlPointInfoFragment newInstance() {
        ControlPointInfoFragment fragment = new ControlPointInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controlpoint_info, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_controlpoint_info);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<ControlPoint> controlPoints = new ArrayList<>(); //TODO заменить класс control point
        for (int i = 0; i< 5; i++) {
            controlPoints.add(new ControlPoint("Контрольная  " + i ));

        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mControlPointsInfoAdapter = new ControlPointsInfoRecyclerAdapter(getActivity(), controlPoints); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mControlPointsInfoAdapter);


        return view;
    }
}
