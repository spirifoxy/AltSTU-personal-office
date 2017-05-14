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
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.TimetableRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Day;
import com.tolichp.spirifoxy.altstu_personal_office.data.Lesson;

import java.util.ArrayList;

public class TimetableFragment extends Fragment {


//    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_PAGE_NAME = "arg_page_name";
    static final String ARGUMENT_LESSONS = "arg_lessons";

    //int pageNumber;
    //int backColor;

    private RecyclerView mRecyclerView;

    private TimetableRecyclerAdapter mTimetableAdapter;

    //TODO rename?
    private String pageName;
    private ArrayList<Lesson> lessons;





//    static TimetableFragment newInstance(String pageName, ArrayList<Lesson> lessons) { //int page) {
    static TimetableFragment newInstance(Day day) { //int page) {
        TimetableFragment timeTableFragment = new TimetableFragment();
        Bundle arguments = new Bundle();
//        arguments.putInt(ARGUMENT_PAGE_NUMBER, day);

        //test
        //arguments.putInt("data", R.drawable.altgtu);//getBgRes()[day.testNumber]);
        //test



        arguments.putString(ARGUMENT_PAGE_NAME, day.getName());
        arguments.putParcelableArrayList(ARGUMENT_LESSONS, day.getLessons());
        timeTableFragment.setArguments(arguments);
        return timeTableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        pageName = getArguments().getString(ARGUMENT_PAGE_NAME);
        lessons = getArguments().getParcelableArrayList(ARGUMENT_LESSONS);

        //Random rnd = new Random();
        //backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, null);

        /*TextView tvPage = (TextView) view.findViewById(R.id.textview_page);
        tvPage.setText("Page " + pageName);//pageNumber);*/




        //tvPage.setBackgroundColor(backColor);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.view_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator()); //TODO test please

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?

        mTimetableAdapter = new TimetableRecyclerAdapter(lessons);
        mRecyclerView.setAdapter(mTimetableAdapter);




        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
