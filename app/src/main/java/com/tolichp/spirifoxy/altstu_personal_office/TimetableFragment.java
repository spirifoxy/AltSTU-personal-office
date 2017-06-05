package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tolichp.spirifoxy.altstu_personal_office.app.AppController;
import com.tolichp.spirifoxy.altstu_personal_office.controller.TimetableController;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyGroup;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;
import com.tolichp.spirifoxy.altstu_personal_office.utils.ServerCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class TimetableFragment extends Fragment {

    static final String TAG = "myLogs";

    private ScrollerViewPager viewPager;

    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter pagerAdapter; //TODO убрать? мб сразу созданный объект слать куда там надо
    private Spinner mCountrySpinner;

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_WEEK_VIEW = 2;
    private int mWeekViewType = TYPE_DAY_VIEW;

//    private String URL_DAYS = "http://altstu-server/web/api/v1/timetables/all";
    private ArrayList<Day> days;
    
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
        days = new ArrayList<>();




        Locale locale = new Locale("ru","RU");
//        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
//        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        Date currentDate = new Date();
//        cal.setTime(currentDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        String today = sdf.format(currentDate);



        // prepare the Request
        /*DateFormat df = new SimpleDateFormat("Y-m-d");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);*/

        StudyGroup newGroup = new StudyGroup();

        TimetableController controller = new TimetableController();
        controller.getTwooWeeksTimetable(newGroup, today, getContext(), new ServerCallback() {
                    @Override
                    public void onSuccess(JSONArray response) {
                        Log.d("Response", response.toString());

                        parseJsonDays(response);


                        Fragment fragment = TTDayFragment.newInstance(days);

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_timetable, fragment);
                        transaction.commit();

                    }
                }
        );

        /*Fragment fragment = TTDayFragment.newInstance(days);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_timetable, fragment);
        transaction.commit();*/

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.week_menu, menu);
        inflater.inflate(R.menu.datapicker_menu, menu);
        inflater.inflate(R.menu.change_tt_view_menu, menu);

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
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;

                    Fragment fragment = TTDayFragment.newInstance(days);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_timetable, fragment);
                    transaction.commit();

                }
                return true;

            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;

                    Fragment fragment = TTWeekFragment.newInstance();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_timetable, fragment);
                    transaction.commit();

                }
                return true;
        }
        return false;
    }


    private int getWeekNumber(Date date) {
        Locale locale = new Locale("ru","RU");
        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        cal.setTime(date);

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
        if (date.after(firstSemDate) && date.before(secondSemDate)) { //первый семестр
            semBegin.setTime(firstSemDate);
        } else {
            semBegin.setTime(secondSemDate);
        }
        return week - semBegin.get(Calendar.WEEK_OF_YEAR) + 1;
    }

    private int getCurrentWeekNumber() {
        return getWeekNumber(new Date());
    }

    private void parseJsonDays(JSONArray response) {
        Locale locale = new Locale("ru","RU");
        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
//        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        Calendar cal = GregorianCalendar.getInstance(tz, locale);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", locale);


        Map<String,ArrayList<JSONObject>> hmDays = new HashMap<>();
        List<String> dates = new ArrayList<>();
        try {
//            Locale locale = new Locale("ru","RU");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);


            for (int i = 0; i < response.length(); i++) {
                JSONObject lessonObject = response.getJSONObject(i);
                String lessonDateString = lessonObject.getString("begindatetime").split("T")[0]; //отсекаем время
                dates.add(lessonDateString);

                ArrayList<JSONObject> lessons;
                if(hmDays.containsKey(lessonDateString)){
                    // if the key has already been used,
                    // we'll just grab the array list and add the value to it
                    lessons = hmDays.get(lessonDateString);
                    lessons.add(lessonObject);
                } else {
                    // if the key hasn't been used yet,
                    // we'll create a new ArrayList<String> object, add the value
                    // and put it in the array list with the new key
                    lessons = new ArrayList<>();
                    lessons.add(lessonObject);
                    hmDays.put(lessonDateString, lessons);
                }
            }
//          2017-06-05
            String tetetet = "2017-06-05";
            ArrayList day1 = hmDays.get(tetetet);
//            Toast.makeText(getContext(), "test ", Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Set<String> datesUnique = new HashSet<String>(dates).toArray(new String[0]);

        Set<String> temp = new HashSet<String>(dates);
        String[] uniqueDates = temp.toArray(new String[temp.size()]);


        for (String date: uniqueDates) {
            ArrayList<JSONObject> dayLessonObjects = hmDays.get(date);


            Day day = new Day();



            Date d = new Date();
            try {
                d = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            cal.setTime(d);
            int dayOfWeekNumber = cal.get(Calendar.DAY_OF_WEEK);

            String[] weekNamesShort = getResources().getStringArray(R.array.week_days_short);
            String dayOfWeekName = weekNamesShort[dayOfWeekNumber-1];


            day.setDate(date);
            day.setName(dayOfWeekName);

            ArrayList<Lesson> lessons = new ArrayList<>();

            for (JSONObject lessonObject: dayLessonObjects) {
                try {
                    Lesson lesson = new Lesson();
                    JSONObject roomObj = lessonObject.getJSONObject("room");
                    JSONObject buildingObj = roomObj.getJSONObject("building");

                    String roomName = roomObj.getInt("number") + buildingObj.getString("name");

                    String info = "";

                    if (BuildConfig.FLAVOR.equals("student")) {
                        JSONObject teacherObj = lessonObject.getJSONObject("teacher");
                        info = teacherObj.getString("name") + " " + teacherObj.getString("patronymic") + " " + teacherObj.getString("surname"); //
                    } else {
                        JSONObject groupObj = lessonObject.getJSONObject("studygroup");
                        info = groupObj.getString("name1") + "-" + groupObj.getString("name2");
                    }

                    JSONObject typeLessonObj = lessonObject.getJSONObject("typelesson");
                    String typeLesson = typeLessonObj.getString("typeshort");

                    String startTimeString = lessonObject.getString("begindatetime");//.split("T")[1]; //берем время
//                    Calendar startTime = new GregorianCalendar();//TimeZone.getTimeZone(startTimeString));
                    try {
                        d = sdfTime.parse(startTimeString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    long ms = d.getTime();
                    Calendar startCal = GregorianCalendar.getInstance(locale);
//                    startCal.setTime(d);//setTimeInMillis(msStartTime);

                    startCal.add(Calendar.MINUTE, 0);
                    startCal.setTimeInMillis(d.getTime());
                    lesson.setStartTime(startCal);

                    Calendar endCal = GregorianCalendar.getInstance(locale);
                    endCal.setTimeInMillis(d.getTime());
                    endCal.add(Calendar.MINUTE, 90);
                    lesson.setEndTime(endCal);



                    lesson.setTitle("Математический анализ");
                    lesson.setAudience(roomName);
                    lesson.setClassType(typeLesson);
                    lesson.setInfo(info);
//                    lesson.setStartTime(cal);

                    lessons.add(lesson);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            day.setLessons(lessons);
            days.add(day);
        }

//        listAdapter.notifyDataSetChanged();
    }
}
