package com.tolichp.spirifoxy.altstu_personal_office;

/**
 * Created by Alexandr on 23.05.2017.
 */

import android.content.Context;
import android.os.Bundle;
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
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudyRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.StudyingStudent;

import java.util.ArrayList;
import java.util.Arrays;

public class StudyingStudentsListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private StudyRecyclerAdapter mTStudyAdapter;

    public static StudyingStudentsListFragment newInstance() {
        StudyingStudentsListFragment fragment = new StudyingStudentsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_studyingstudents_list, container, false);
        ArrayAdapter<String> spinnerArrayAdapter;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_study);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<StudyingStudent> subjects = new ArrayList<>();
        for (int i = 0; i< 13; i++) {
            subjects.add(new StudyingStudent(i, "Иванов " + i ));
        }

        Spinner groupSpinner = (Spinner) view.findViewById(R.id.spinner_studyingstudentgroup);
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

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mTStudyAdapter = new StudyRecyclerAdapter(getActivity(), subjects); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mTStudyAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private AdapterView.OnItemSelectedListener mSpinnerItemSelectListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItemText = (String) parent.getItemAtPosition(position);
            // If user change the default selection
            // First item is disable and it is used for hint
            /*if (position > 0) {
                // Notify the selected item text
                Toast.makeText
                        (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}

