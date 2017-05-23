package com.tolichp.spirifoxy.altstu_personal_office;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsInfoRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.adapter.ControlPointsRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.ControlPoint;
import com.tolichp.spirifoxy.altstu_personal_office.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlPointInfoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ControlPointsInfoRecyclerAdapter mControlPointsInfoAdapter;
    private ArrayList<ControlPoint> controlPoints;

    public static ControlPointInfoFragment newInstance() {
        ControlPointInfoFragment fragment = new ControlPointInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controlpoint_info, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_controlpoint_info);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        controlPoints = new ArrayList<>(); //TODO заменить класс control point
        for (int i = 0; i< 5; i++) {
            controlPoints.add(new ControlPoint("Контрольная  " + i ));

        }

        //TODO элементы должны быть имя + срок как минимум

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mControlPointsInfoAdapter = new ControlPointsInfoRecyclerAdapter(getActivity(), controlPoints); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mControlPointsInfoAdapter);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.add_menu, menu);
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //menu.findItem(R.id.action_add).setVisible(false).setEnabled(false); //скрываем кнопку добавления с предыдущего экрана
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                initAddCPDialog();
                return true;
        }
        return false;
    }

    private void initAddCPDialog() {
        final Dialog dialog = new Dialog(getContext());
//        dialog.setTitle("NumberPicker");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_numberpicker);
        Button buttonOk = (Button) dialog.findViewById(R.id.button_ok);
        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);


        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(getContext(), "testVC " + newVal, Toast.LENGTH_SHORT).show();
            }
        });


        buttonOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "testOC " + np.getValue(), Toast.LENGTH_SHORT).show();
                //tv.setText(String.valueOf(np.getValue()));
                for (int i = 0; i < np.getValue(); i++) {
                    controlPoints.add(new ControlPoint("Добавилось " + i));
                }
                mControlPointsInfoAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
