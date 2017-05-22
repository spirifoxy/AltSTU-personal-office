package com.tolichp.spirifoxy.altstu_personal_office;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudentsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by spirifoxy on 21.05.2017.
 */

public class AttendanceFragment extends Fragment {

    StudentsRecyclerAdapter mStudentsRecyclerAdapter;

    public static AttendanceFragment newInstance() {
        AttendanceFragment fragment = new AttendanceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ArrayAdapter<String> spinnerArrayAdapter;



        Spinner disciplineSpinner = (Spinner) view.findViewById(R.id.spinner_discipline);
        String[] disciplines = new String[]{
                "Дисциплина...",
                "Дисциплина 1",
                "Дисциплина 2",
                "Дисциплина 3",
        };
        ArrayList<String> disciplinesList = new ArrayList<>(Arrays.asList(disciplines));
        spinnerArrayAdapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, disciplinesList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        disciplineSpinner.setAdapter(spinnerArrayAdapter);
        disciplineSpinner.setOnItemSelectedListener(mSpinnerItemSelectListener);


        Spinner timeSpinner = (Spinner) view.findViewById(R.id.spinner_time);
        String[] time = new String[]{
                "Время...",
                "8:15 - 9:45",
                "9:55 - 11:25",
                "11:35 - 13:05",
        };
        ArrayList<String> timeList = new ArrayList<>(Arrays.asList(time));
        spinnerArrayAdapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, timeList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        timeSpinner.setAdapter(spinnerArrayAdapter);
        timeSpinner.setOnItemSelectedListener(mSpinnerItemSelectListener);


        Spinner groupSpinner = (Spinner) view.findViewById(R.id.spinner_group);
        String[] groups = new String[]{
                "Группа...",
                "ПИ-31",
                "ПИ-32",
        };
        ArrayList<String> groupsList = new ArrayList<>(Arrays.asList(groups));
        spinnerArrayAdapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, groupsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        groupSpinner.setAdapter(spinnerArrayAdapter);
        groupSpinner.setOnItemSelectedListener(mSpinnerItemSelectListener);



        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i< 20; i++) {
            Student s = new Student();
            s.name = "Name";
            s.surname = "Surname";
            students.add(s);

        }

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_students);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mStudentsRecyclerAdapter = new StudentsRecyclerAdapter(getActivity(), students); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mStudentsRecyclerAdapter);



        return view;
    }


    private AdapterView.OnItemSelectedListener mSpinnerItemSelectListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItemText = (String) parent.getItemAtPosition(position);
            // If user change the default selection
            // First item is disable and it is used for hint
            if (position > 0) {
                // Notify the selected item text
                Toast.makeText
                        (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


}
