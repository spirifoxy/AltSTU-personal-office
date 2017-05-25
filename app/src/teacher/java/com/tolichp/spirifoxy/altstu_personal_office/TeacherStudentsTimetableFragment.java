package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherStudentsTimetableFragment extends TimetableFragment {

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

        Fragment fragment = TTDayFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_timetable, fragment);
        transaction.commit();

        return view;
    }


    public static TeacherStudentsTimetableFragment newInstance() {//String param1, String param2) {
        TeacherStudentsTimetableFragment fragment = new TeacherStudentsTimetableFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

}
