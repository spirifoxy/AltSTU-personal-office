package com.tolichp.spirifoxy.altstu_personal_office;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudyprogressRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;

public class StudyProgressFragment extends android.support.v4.app.Fragment {

    /*private ScrollerViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter weekPagerAdapter;*/

    private RecyclerView mRecyclerView;
    private StudyprogressRecyclerAdapter mTStudyprogressAdapter;

    public static StudyProgressFragment newInstance() {//String param1, String param2) {
        StudyProgressFragment fragment = new StudyProgressFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        setContentView(R.layout.fragment_studyprogress);
        View view = inflater.inflate(R.layout.fragment_studyprogress, container, false);

        /*Fragment sf  = new SubjectFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_studyprogress, sf);
        ft.addToBackStack(null);
        ft.commit();*/


        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_studyprogress);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = 0; i< 13; i++) {
            subjects.add(new Subject("test subject " + i ));

        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mTStudyprogressAdapter = new StudyprogressRecyclerAdapter(subjects);
        mRecyclerView.setAdapter(mTStudyprogressAdapter);

        return view;
    }
}
