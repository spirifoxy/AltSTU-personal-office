package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.utils.DatePicker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by spirifoxy on 25.05.2017.
 */

public class JournalFragment extends Fragment {

    private ViewPagerAdapter listAdapter;

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private DatePicker dateDialog;

    public static JournalFragment newInstance() {
        JournalFragment fragment = new JournalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.attendance));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.pager_journal);
        fragmentsList = new ArrayList<>();


        fragmentsList.add(new AttendanceFragment());
        fragmentsList.add(new MarksFragment());


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout_journal);
        tabLayout.setupWithViewPager(viewPager);

        //PagerTitleStrip pagerTabStrip = (PagerTitleStrip) view.findViewById(R.id.pager_header_journal);

//        PagerTabStrip pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pager_header_journal);
//        pagerTabStrip.setDrawFullUnderline(true);

        String[] titlesList = getResources().getStringArray(R.array.journal_titles);
//                { getResources().getString(R.string.attendance), getResources().getString(R.string.study) };


        listAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentsList, titlesList);
        viewPager.setAdapter(listAdapter);






        ArrayAdapter<String> spinnerArrayAdapter;

        final Button buttonDate = (Button) view.findViewById(R.id.button_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog = DatePicker.newInstance();//new DatePicker();

            }
        });

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







        return view;
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
