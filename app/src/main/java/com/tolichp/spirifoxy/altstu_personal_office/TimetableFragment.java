package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;

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

public class TimetableFragment extends Fragment {

    /* public TimetableFragment() {
         // Required empty public constructor
     }*/
    static final String TAG = "myLogs";

    private ScrollerViewPager viewPager;

    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter pagerAdapter; //TODO убрать? мб сразу созданный объект слать куда там надо
    private Spinner mCountrySpinner;


    public static TimetableFragment newInstance() {//String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        getActivity().setTitle(getResources().getString(R.string.timetable));
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        fragmentsList = new ArrayList<>();

        viewPager = (ScrollerViewPager) view.findViewById(R.id.view_pager_timetable);
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

        //int currentWeekNumber = getCurrentWeekNumber();

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
            TimetableDayFragment timetableFragment = TimetableDayFragment.newInstance(day);
            fragmentsList.add(timetableFragment);
        }

        String[] titlesList = getResources().getStringArray(R.array.week_days_short);
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentsList, titlesList);

        viewPager.setAdapter(pagerAdapter);
        viewPager.fixScrollSpeed();


        final SpringIndicator springIndicator = (SpringIndicator) view.findViewById(R.id.indicator);
        springIndicator.setViewPager(viewPager);



        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.week_menu, menu);
        inflater.inflate(R.menu.datapicker_menu, menu);

        /*MenuItem btn = menu.findItem(R.id.action_datapicker);
        btn.setBackgroundResource(R.drawable.ic_launcher);*/

        MenuItem menuItem = menu.findItem(R.id.spinner_week);
        mCountrySpinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.week_numbers));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountrySpinner.setAdapter(countryAdapter);
    }

    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_to_change_icon_for); // You can change the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_datapicker:
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getActivity().getSupportFragmentManager(), "datePicker");
                return true;
            /*case R.id.action_2:

                return true;*/
        }
        return false;
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
