package com.tolichp.spirifoxy.altstu_personal_office;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudyProgressRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;

public class StudyProgressFragment extends android.support.v4.app.Fragment {

    /*private ScrollerViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter weekPagerAdapter;*/


    public static StudyProgressFragment newInstance() {//String param1, String param2) {
        StudyProgressFragment fragment = new StudyProgressFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.studyprogress));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //        setContentView(R.layout.fragment_studyprogress);
        View view = inflater.inflate(R.layout.fragment_studyprogress, container, false);


        Fragment fragment = SubjectsListFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_studyprogress, fragment);
        transaction.commit();

        return view;


    }
}
