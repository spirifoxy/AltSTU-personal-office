package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudentsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Student;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alexandr on 23.05.2017.
 */

public class StudyFragment extends android.support.v4.app.Fragment {

    public static StudyFragment newInstance() {//String param1, String param2) {
        StudyFragment fragment = new StudyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_study, container, false);

        Fragment fragment = StudyingStudentsListFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_study, fragment);
        transaction.commit();

        return view;
    }
}
