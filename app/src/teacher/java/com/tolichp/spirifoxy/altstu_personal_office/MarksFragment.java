package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.PointsInMarksAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudentsInMarksAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.aligningrecyclerview.AligningRecyclerView;
import com.tolichp.spirifoxy.altstu_personal_office.data.Mark;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyingStudent;

import java.util.ArrayList;

/**
 * Created by Alexandr on 23.05.2017.
 */

public class MarksFragment extends android.support.v4.app.Fragment {

    private AligningRecyclerView mRecyclerView;
    private ArrayList<StudyingStudent> studyingStudents;
    private StudentsInMarksAdapter studentsInMarksAdapter1;

    private AligningRecyclerView mRecyclerView2;
    private ArrayList<Mark> marks;
    private PointsInMarksAdapter pointsInMarksAdapter;

    public static MarksFragment newInstance() {//String param1, String param2) {
        MarksFragment fragment = new MarksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_marks, container, false);


        mRecyclerView = (AligningRecyclerView) view.findViewById(R.id.aligning1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        studyingStudents = new ArrayList<>();
        for (int i = 0; i< 23; i++) {
            studyingStudents.add(new StudyingStudent(i, "Ivanov Ivan"));
        }


        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        studentsInMarksAdapter1 = new StudentsInMarksAdapter(studyingStudents); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(studentsInMarksAdapter1);

        mRecyclerView2 = (AligningRecyclerView) view.findViewById(R.id.aligning2);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());

        marks = new ArrayList<>();
        for (int i = 0; i< 23; i++) {
            marks.add(new Mark(25));
        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        pointsInMarksAdapter = new PointsInMarksAdapter(marks); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView2.setAdapter(pointsInMarksAdapter);


        mRecyclerView.bindTo(mRecyclerView2,AligningRecyclerView.ALIGN_ORIENTATION_VERTICAL);
        mRecyclerView2.bindTo(mRecyclerView,AligningRecyclerView.ALIGN_ORIENTATION_VERTICAL);

        return view;
    }
}
