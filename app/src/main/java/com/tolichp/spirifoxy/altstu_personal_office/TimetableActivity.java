package com.tolichp.spirifoxy.altstu_personal_office;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.WeekFragmentPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class TimetableActivity extends AppCompatActivity {//FragmentActivity {//AppCompatActivity {

    static final String TAG = "myLogs";

//    private ViewPager pager;
    private ScrollerViewPager viewPager;

    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter weekPagerAdapter; //TODO убрать? мб сразу созданный объект слать куда там надо


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        fragmentsList = new ArrayList<>();

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            for (int i = 0; i < weekDays.length-1; i++) {
                days.add(new Day(weekDays[i+1], i));
            }
        }

        for (Day day : days) {
            //TimetableFragment timetableFragment = new TimetableFragment(day);
            TimetableFragment timetableFragment = TimetableFragment.newInstance(day);
            //timetableFragment.setCurrentPage
            fragmentsList.add(timetableFragment);
        }

        String[] titlesList = getResources().getStringArray(R.array.week_days_short);
        weekPagerAdapter = new WeekFragmentPagerAdapter(getSupportFragmentManager(),fragmentsList, titlesList);

        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);*/

        //PagerAdapter adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(weekPagerAdapter);
        viewPager.fixScrollSpeed();


        final SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        springIndicator.setViewPager(viewPager);
        springIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





    /*private String[] getTitles(){

        return new String[]{"page1", "page2", "page3", "page4"};
    }

    private Integer[] getBgRes(){
        return new Integer[] {R.drawable.altgtu, R.drawable.altgtu111, R.drawable.altgtu_test};//{R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4};
    }*/

    /*class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            GuideFragment  fragment = new GuideFragment ();
            Bundle args = new Bundle();
            args.putInt("data",getBgRes()[position]);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTitles()[position];
        }
    }*/
}
