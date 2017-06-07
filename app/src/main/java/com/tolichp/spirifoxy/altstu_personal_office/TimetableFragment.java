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

import com.tolichp.spirifoxy.altstu_personal_office.controller.TimetableController;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyGroup;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;
import com.tolichp.spirifoxy.altstu_personal_office.utils.ServerCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    private ArrayList<Day> days;

    //Privorotskij
    private ArrayList<ArrayList<Day>> weeks;
    int currentWeekNumber;
    
    public static TimetableFragment newInstance() {//String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.timetable));
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        //Privorotskij
        //Массив с двумя неделями, заполнен по 6 дней каждая
        weeks = new ArrayList<>();
        weeks.add(new ArrayList<Day>());
        weeks.add(new ArrayList<Day>());
        for(int i = 0; i < 6; i++) weeks.get(0).add(new Day());
        for(int i = 0; i < 6; i++) weeks.get(1).add(new Day());
        currentWeekNumber = getCurrentWeekNumber();

        //TODO: отобразить в селекторе недели нужную неделю


        //Заполняем массив недель
        //(сначала первую, потом вторую неделю-строку) датами
        ArrayList<String> datesOfWeek= new ArrayList<>();

        datesOfWeek = getDatesOfCurrentWeek();
        for(int i = 0; i < 6; i++)
            weeks.get(currentWeekNumber%2).
                    get(i).setDate(
                            datesOfWeek.get(i));

        datesOfWeek = getDatesOfNextWeek();
        for(int i = 0; i < 6; i++)
            weeks.get((currentWeekNumber+1)%2).
                    get(i).setDate(
                    datesOfWeek.get(i));

        days = new ArrayList<>();

        Locale locale = new Locale("ru","RU");
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        String today = dateFormat.format(currentDate);

        StudyGroup newGroup = new StudyGroup();

        TimetableController controller = new TimetableController();
        controller.getTwoWeeksTimetable(newGroup, today, new ServerCallback() {
                    @Override
                    public void onSuccess(JSONArray response) {
                        Log.d("Response", response.toString());

                        parseJsonDays(response);

                        Fragment fragment = TTDayFragment.newInstance(days,weeks, currentWeekNumber);

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_timetable, fragment);
                        transaction.commit();

                    }
                }
        );
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

                    Fragment fragment = TTDayFragment.newInstance(days,weeks, currentWeekNumber);

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
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Barnaul");

        Calendar calendar = GregorianCalendar.getInstance(timeZone, locale);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", locale);

        //в хэш-мап складываются занятия по ключу даты
        Map<String,ArrayList<JSONObject>> hmDays = new HashMap<>();
        List<String> dates = new ArrayList<>();
        try {
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //складываем в weeks пары, извлекая их по ключу из хэшмапа
        for(int i=0; i < weeks.size(); i++ )
            for (int j = 0; j < weeks.get(i).size(); j++)
            {
                try {


                    //если есть пары с такой датой
                    if (hmDays.containsKey(weeks.get(i).get(j).getDate())) {
                        //то будем собирать объект lesson и добавлять к нужному дню
                        ArrayList<JSONObject> lessons;
                        lessons = hmDays.get(weeks.get(i).get(j).getDate());

                        //вытащили пары из хэшмапа, теперь каждую нужно положить в нужный day
                        for (int k = 0; k < lessons.size(); k++) {
                            Lesson lessonToAdd = new Lesson();
                            JSONObject lessonObject = lessons.get(k);
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
                            Date d = null;
                            try {
                                d = dateFormatTime.parse(startTimeString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar startCal = GregorianCalendar.getInstance(locale);

                            startCal.add(Calendar.MINUTE, 0);
                            startCal.setTimeInMillis(d.getTime());
                            lessonToAdd.setStartTime(startCal);

                            Calendar endCal = GregorianCalendar.getInstance(locale);
                            endCal.setTimeInMillis(d.getTime());
                            endCal.add(Calendar.MINUTE, 90);
                            lessonToAdd.setEndTime(endCal);

                            //TODO: ссделать нормальное имя дисциплины
                            lessonToAdd.setTitle("Математический анализ");
                            lessonToAdd.setAudience(roomName);
                            lessonToAdd.setClassType(typeLesson);
                            lessonToAdd.setInfo(info);

                            weeks.get(i).get(j).addLesson(lessonToAdd);
                        }
                    }
                }
                catch (JSONException e)
                {

                }
            }


//        Set<String> temp = new HashSet<>(dates);
//        String[] uniqueDates = temp.toArray(new String[temp.size()]);



        //сортировка дней по датам. это не помогает, т.к.
        //если в какой-то день нет пар, то он схлопывается в массиве дней
//        Collections.sort(dates, new Comparator<String>() {
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });

//        for (String date: uniqueDates) {
//            ArrayList<JSONObject> dayLessonObjects = hmDays.get(date);
//
//            Day day = new Day();
//
//            Date d = new Date();
//            try {
//                d = dateFormat.parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            calendar.setTime(d);
//            int dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK);

            /*Calendar is American, so it's neccessary to substract 2, bugfix, bad*/
//
//            String[] weekNamesShort = getResources().getStringArray(R.array.week_days_short);
//
//            //TODO: добавить трай/кэтч на воскресенье (dayOfWeek==1)
//            String dayOfWeekName = weekNamesShort[dayOfWeekNumber-2];
//
//            day.setDate(date);
//
//            day.setName(dayOfWeekName);
//
//            ArrayList<Lesson> lessons = new ArrayList<>();
//
//            for (JSONObject lessonObject: dayLessonObjects) {
//                try {
//                    Lesson lesson = new Lesson();
//                    JSONObject roomObj = lessonObject.getJSONObject("room");
//                    JSONObject buildingObj = roomObj.getJSONObject("building");
//
//                    String roomName = roomObj.getInt("number") + buildingObj.getString("name");
//
//                    String info = "";
//
//                    if (BuildConfig.FLAVOR.equals("student")) {
//                        JSONObject teacherObj = lessonObject.getJSONObject("teacher");
//                        info = teacherObj.getString("name") + " " + teacherObj.getString("patronymic") + " " + teacherObj.getString("surname"); //
//                    } else {
//                        JSONObject groupObj = lessonObject.getJSONObject("studygroup");
//                        info = groupObj.getString("name1") + "-" + groupObj.getString("name2");
//                    }
//
//                    JSONObject typeLessonObj = lessonObject.getJSONObject("typelesson");
//                    String typeLesson = typeLessonObj.getString("typeshort");
//
//                    String startTimeString = lessonObject.getString("begindatetime");//.split("T")[1]; //берем время
//                    try {
//                        d = dateFormatTime.parse(startTimeString);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    Calendar startCal = GregorianCalendar.getInstance(locale);
//
//                    startCal.add(Calendar.MINUTE, 0);
//                    startCal.setTimeInMillis(d.getTime());
//                    lesson.setStartTime(startCal);
//
//                    Calendar endCal = GregorianCalendar.getInstance(locale);
//                    endCal.setTimeInMillis(d.getTime());
//                    endCal.add(Calendar.MINUTE, 90);
//                    lesson.setEndTime(endCal);
//
//                    lesson.setTitle("Математический анализ");
//                    lesson.setAudience(roomName);
//                    lesson.setClassType(typeLesson);
//                    lesson.setInfo(info);
//
//                    lessons.add(lesson);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////            }
//
//            day.setLessons(lessons);
//            days.add(day);
//        }

//        listAdapter.notifyDataSetChanged();
    }

    public ArrayList<String> getDatesOfWeek(Date date) {
        ArrayList<String> answerDates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Устанавливаем текущее время

        //Далее в методе дата устанавливается на понедельник текущей недели
        //В календаре воскресенье - 1 день недели
        //Если мы хотим при установке даты на понедельник остаться на текущей неделе
        //Сбросим дату на субботу
        //TODO: проверить, как ведёт себя в месяце, в котором первое число воскресенье
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        if(today == 1) calendar.add(Calendar.DAY_OF_YEAR, -1);

        Locale locale = new Locale("ru","RU");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);

        //Устанавливаем понедельник на календаре, будто сейчас понедельник
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i < 6; i++) {
            answerDates.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1); //Прибавляем сутки
        }

        return answerDates;
    }

    public ArrayList<String> getDatesOfCurrentWeek() {
        return getDatesOfWeek(new Date());
    }

    public ArrayList<String> getDatesOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Устанавливаем текущее время
        calendar.add(Calendar.DAY_OF_YEAR,7);
        return getDatesOfWeek(calendar.getTime());
    }
}
