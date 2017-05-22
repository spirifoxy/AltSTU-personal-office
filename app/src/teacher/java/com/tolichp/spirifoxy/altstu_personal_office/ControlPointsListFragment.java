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

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.ControlPoint;

import java.util.ArrayList;


public class ControlPointsListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ControlPointsRecyclerAdapter mControlPointsAdapter;

    public static ControlPointsListFragment newInstance() {
        ControlPointsListFragment fragment = new ControlPointsListFragment();
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

        View view = inflater.inflate(R.layout.fragment_controlpoints_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_controlpoints);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<ControlPoint> controlPoints = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            controlPoints.add(new ControlPoint("test control point " + i ));

        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mControlPointsAdapter = new ControlPointsRecyclerAdapter(getActivity(), controlPoints); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mControlPointsAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
