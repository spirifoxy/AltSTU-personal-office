package com.tolichp.spirifoxy.altstu_personal_office;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.TimetableRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;

import java.util.ArrayList;

public class TimetableDayFragment extends Fragment {


//    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_DAY = "arg_page_name";
    static final String ARGUMENT_LESSONS = "arg_lessons";

    //int pageNumber;
    //int backColor;

    private RecyclerView mRecyclerView;

    private TimetableRecyclerAdapter mTimetableAdapter;

    //TODO rename?
    private Day day;
//    private ArrayList<Lesson> lessons;





//    static TimetableDayFragment newInstance(String day, ArrayList<Lesson> lessons) { //int page) {
    static TimetableDayFragment newInstance(Day day) { //int page) {
        TimetableDayFragment timeTableFragment = new TimetableDayFragment();
        Bundle arguments = new Bundle();
//        arguments.putInt(ARGUMENT_PAGE_NUMBER, day);

        //test
        //arguments.putInt("data", R.drawable.altgtu);//getBgRes()[day.testNumber]);
        //test



        arguments.putParcelable(ARGUMENT_DAY, day);
//        arguments.putParcelableArrayList(ARGUMENT_LESSONS, day.getLessons());
        timeTableFragment.setArguments(arguments);
        return timeTableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        day = getArguments().getParcelable(ARGUMENT_DAY);
//        lessons = getArguments().getParcelableArrayList(ARGUMENT_LESSONS);

        //Random rnd = new Random();
        //backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable_day, container, false);

        /*TextView tvPage = (TextView) view.findViewById(R.id.textview_page);
        tvPage.setText("Page " + day);//pageNumber);*/
        //tvPage.setBackgroundColor(backColor);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.view_recycler_timetable);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?

        mTimetableAdapter = new TimetableRecyclerAdapter(day.getLessons());//lessons);
        mRecyclerView.setAdapter(mTimetableAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
