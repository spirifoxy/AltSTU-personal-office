package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class TimetableActivity extends AppCompatActivity {

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

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager_timetable);
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

        int currentWeekNumber = getCurrentWeekNumber();



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
            TimetableFragment timetableFragment = TimetableFragment.newInstance(day);
            fragmentsList.add(timetableFragment);
        }

        String[] titlesList = getResources().getStringArray(R.array.week_days_short);
        weekPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentsList, titlesList);

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

    private int getCurrentWeekNumber() {
        Locale locale = new Locale("ru","RU");
        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        Date currentDate = new Date();
        cal.setTime(currentDate);

        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);

        Date firstSemDate = null;
        Date secondSemDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", locale);
        try {firstSemDate = sdf.parse(getResources().getString(R.string.first_sep) + "-" + year);
            secondSemDate = sdf.parse(getResources().getString(R.string.first_feb) + "-" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar semBegin = GregorianCalendar.getInstance(tz, locale);
        if (currentDate.after(firstSemDate) && currentDate.before(secondSemDate)) { //первый семестр
            semBegin.setTime(firstSemDate);
        } else {
            semBegin.setTime(secondSemDate);
        }
        return week - semBegin.get(Calendar.WEEK_OF_YEAR) + 1;
    }
}
