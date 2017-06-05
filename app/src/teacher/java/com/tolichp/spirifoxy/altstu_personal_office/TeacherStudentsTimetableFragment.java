package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.controller.TimetableController;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyGroup;
import com.tolichp.spirifoxy.altstu_personal_office.utils.ServerCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class TeacherStudentsTimetableFragment extends TimetableFragment {

    private ArrayList<Day> days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable_groups, container, false);

        Spinner groupSpinner = (Spinner) view.findViewById(R.id.spinner_groupTimetable);
        String[] groups = new String[]{
                "Группа...",
                "ПИ-31",
                "ПИ-32",
        };
        ArrayList<String> groupsList = new ArrayList<>(Arrays.asList(groups));

        ArrayAdapter<String> spinnerArrayAdapter;
        spinnerArrayAdapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, groupsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        groupSpinner.setAdapter(spinnerArrayAdapter);


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
                        /*try {
                            Log.d("Response", response.getJSONArray(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        Log.d("Response", response.toString());

                        parseJsonDays(response);


                        Fragment fragment = TTDayFragment.newInstance(days);

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_timetable, fragment);
                        transaction.commit();

                    }
                }
        );






        return view;
    }


    public static TeacherStudentsTimetableFragment newInstance() {//String param1, String param2) {
        TeacherStudentsTimetableFragment fragment = new TeacherStudentsTimetableFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }























    private void parseJsonDays(JSONArray response) {
        Locale locale = new Locale("ru","RU");
//        TimeZone tz = TimeZone.getTimeZone("Asia/Barnaul");
//        Calendar cal = GregorianCalendar.getInstance(tz, locale);
        Calendar cal = GregorianCalendar.getInstance(locale);

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



                    lesson.setTitle("Тестовый предмет");
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
