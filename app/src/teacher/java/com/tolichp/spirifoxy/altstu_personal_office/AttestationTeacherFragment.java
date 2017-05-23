package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttestationRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.AttestatedStudent;

import java.util.ArrayList;
import java.util.Arrays;

public class AttestationTeacherFragment extends android.support.v4.app.Fragment {

    AttestationRecyclerAdapter attestationRecyclerAdapter;

    public static AttestationTeacherFragment newInstance() {//String param1, String param2) {
        AttestationTeacherFragment fragment = new AttestationTeacherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.attestation));
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


        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_attestation, container, false);

            ArrayAdapter<String> spinnerArrayAdapter;


            Spinner disciplineSpinner = (Spinner) view.findViewById(R.id.at_spinner_discipline);
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

            Spinner groupSpinner = (Spinner) view.findViewById(R.id.at_spinner_group);
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


            ArrayList<AttestatedStudent> students = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                AttestatedStudent s = new AttestatedStudent("Ivanov Ivan", i, 0, 0, 0, 0);
                students.add(s);
            }

            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_attestation);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            //TODO какую-нибудь проверку на то, есть ли элементы в списке?
            attestationRecyclerAdapter = new AttestationRecyclerAdapter(students); //ToDO прочитать про onAttach метод, реализовать его
            mRecyclerView.setAdapter(attestationRecyclerAdapter);

            return view;
        }
    }
