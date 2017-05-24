package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TeacherMessages extends Fragment {

    public static TeacherMessages newInstance() {
        TeacherMessages fragment = new TeacherMessages();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.messages));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        MultiSelectionSpinner multiSpinner = (MultiSelectionSpinner) view.findViewById(R.id.spinner_group);
        multiSpinner.setListener(new MultiSelectionSpinner.OnMultipleItemsSelectedListener() {
            @Override
            public void selectedIndices(List<Integer> indices) {

            }

            @Override
            public void selectedStrings(List<String> strings) {

            }
        });
        ArrayList<String> items = new ArrayList <>();
        items.add("ПИ-31");
        items.add("ПИ-32");
        multiSpinner.setItems(items);
        return view;
    }
}
