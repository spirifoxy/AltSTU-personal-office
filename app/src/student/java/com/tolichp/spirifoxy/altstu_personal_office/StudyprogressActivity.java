package com.tolichp.spirifoxy.altstu_personal_office;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudyprogressRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.TimetableRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;

import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class StudyprogressActivity extends AppCompatActivity {

    /*private ScrollerViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter weekPagerAdapter;*/

    private RecyclerView mRecyclerView;
    private StudyprogressRecyclerAdapter mTStudyprogressAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyprogress);


        /*Fragment sf  = new SubjectFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_studyprogress, sf);
        ft.addToBackStack(null);
        ft.commit();*/


        mRecyclerView = (RecyclerView) findViewById(R.id.view_recycler_studyprogress);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = 0; i< 13; i++) {
            subjects.add(new Subject("test subject " + i ));

        }


        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mTStudyprogressAdapter = new StudyprogressRecyclerAdapter(subjects);
        mRecyclerView.setAdapter(mTStudyprogressAdapter);


    }
}