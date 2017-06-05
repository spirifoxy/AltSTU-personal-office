package com.tolichp.spirifoxy.altstu_personal_office;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.AttendanceSpinnerArrayAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.ControlPoint;

import java.util.ArrayList;
import java.util.Arrays;


public class ControlPointsListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ControlPointsRecyclerAdapter mControlPointsAdapter;
    private ArrayList<ControlPoint> controlPoints;
    private String selectedControlPointType;

    public static ControlPointsListFragment newInstance() {
        ControlPointsListFragment fragment = new ControlPointsListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_controlpoints_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_controlpoints);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        controlPoints = new ArrayList<>();
        controlPoints.add(new ControlPoint("Контрольные работы"));
//        for (int i = 0; i< 5; i++) {
//            controlPoints.add(new ControlPoint("Контрольная работа " + i ));
//
//        }

        Spinner groupSpinner = (Spinner) view.findViewById(R.id.spinner_controlpointsstudentdiscipline);
        String[] groups = new String[]{
                "Дисциплина...",
                "Компьютерная графика",
                "Математический анализ",
        };

        ArrayAdapter<String> spinnerArrayAdapter;
        ArrayList<String> groupsList = new ArrayList<>(Arrays.asList(groups));
        spinnerArrayAdapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, groupsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        groupSpinner.setAdapter(spinnerArrayAdapter);
        groupSpinner.setOnItemSelectedListener(mSpinnerItemSelectListener);

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mControlPointsAdapter = new ControlPointsRecyclerAdapter(getActivity(), controlPoints); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mControlPointsAdapter);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:

                initAddCPTypeDialog();

                return true;
            /*case R.id.action_2:

                return true;*/
        }
        return false;
    }

    private void initAddCPTypeDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_spinner);
        dialog.setCancelable(true);


        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
//                final EditText edittext = (EditText) dialog.findViewById(R.id.editText1);
//                Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel);
        Button buttonOk = (Button) dialog.findViewById(R.id.button_ok);


        final String[] types = new String[]{
                "Тип...",
                "Контрольные",
                "Курсовые",
                "Лабораторные",
        };
        ArrayList<String> disciplinesList = new ArrayList<>(Arrays.asList(types));
        ArrayAdapter<String> adapter = new AttendanceSpinnerArrayAdapter(getContext(), R.layout.item_spinner, disciplinesList);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO проверять, чтобы 0 элемент в массиве был плейсхолдером
                if (position > 0)
                    selectedControlPointType = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedControlPointType == null) {
                    Toast.makeText(getContext(), getResources().getString(R.string.select_control_point_error),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                dialog.dismiss();
                controlPoints.add(new ControlPoint(selectedControlPointType));
                mControlPointsAdapter.notifyDataSetChanged();
                selectedControlPointType = null;
            }
        });

        dialog.show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
