package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ExpandableListView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ExpListAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyItem;

import java.util.ArrayList;

public class StudyingStudentFragment extends Fragment {

    private String[] mGroupsArray = new String[] { "Курсовые работы", "Контрольные работы"};

    private String[] mWinterMonthsArray = new String[] { "Курсовая №1", "Курсовая №2"};
    private String[] mSpringMonthsArray = new String[] { "Контрольная работа №1", "Контрольная работа №2" };
    private WebView mWebView;

    public static StudyingStudentFragment newInstance() {//String param1, String param2) {
        StudyingStudentFragment fragment = new StudyingStudentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.study));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studyingstudent, container, false);

        // Находим наш list
        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.exListView);

        //Создаем набор данных для адаптера
        ArrayList<ArrayList<StudyItem>> groups = new ArrayList<ArrayList<StudyItem>>();
        ArrayList<StudyItem> children1 = new ArrayList<StudyItem>();
        ArrayList<StudyItem> children2 = new ArrayList<StudyItem>();
        children1.add(new StudyItem("Курсовая ",1));
        children1.add(new StudyItem("Курсовая ",2));
        groups.add(children1);
        children2.add(new StudyItem("Курсовая ",1));
        children2.add(new StudyItem("Курсовая ",2));
        children2.add(new StudyItem("Курсовая ",3));
        groups.add(children2);
        //Создаем адаптер и передаем context и список с данными
        ExpListAdapter adapter = new ExpListAdapter(getActivity().getApplicationContext(), groups);
        listView.setAdapter(adapter);

        return view;
    }
}
