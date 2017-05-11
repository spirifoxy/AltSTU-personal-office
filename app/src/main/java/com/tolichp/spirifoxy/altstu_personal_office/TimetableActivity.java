package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.tolichp.spirifoxy.altstu_personal_office.model.Day;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class TimetableActivity extends FragmentActivity {//AppCompatActivity {

    static final String TAG = "myLogs";

    ViewPager pager;
    PagerAdapter weekPagerAdapter; //TODO убрать? мб сразу созданный объект слать куда там надо
    private ArrayList<Fragment> fragmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        fragmentsList = new ArrayList<>();

        pager = (ViewPager) findViewById(R.id.pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //TODO ???
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled, position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged, state = " + state);
            }
        });



        //инициализация дней
        ArrayList<Day> days = null; // TODO not initialized
        //TODO test and debug
        if (null == days) {
            days = new ArrayList<>();
            String[] weekDays = DateFormatSymbols.getInstance().getWeekdays();
            for (int i = 0; i < weekDays.length; i++) {
                days.add(new Day(weekDays[i]));
            }
        }

        for (Day day : days) {
            //TimetableFragment timetableFragment = new TimetableFragment(day);
            TimetableFragment timetableFragment = TimetableFragment.newInstance(day);
            //timetableFragment.setCurrentPage
            fragmentsList.add(timetableFragment);
        }
        weekPagerAdapter = new WeekFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList);
        pager.setAdapter(weekPagerAdapter);
    }




    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_test);
    }*/
}
