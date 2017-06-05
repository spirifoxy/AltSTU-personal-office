package com.tolichp.spirifoxy.altstu_personal_office;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.StudyProgressRecyclerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.Subject;

import java.util.ArrayList;


public class SubjectsListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private StudyProgressRecyclerAdapter mTStudyprogressAdapter;

    public static SubjectsListFragment newInstance() {
        SubjectsListFragment fragment = new SubjectsListFragment();
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

        View view = inflater.inflate(R.layout.fragment_subjects_list, container, false);

        /*Fragment sf  = new SubjectFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_studyprogress, sf);
        ft.addToBackStack(null);
        ft.commit();*/


        mRecyclerView = (RecyclerView) view.findViewById(R.id.view_recycler_studyprogress);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = 1; i< 13; i++) {
            subjects.add(new Subject("Математический анализ " + i ));

        }

        //TODO какую-нибудь проверку на то, есть ли элементы в списке?
        mTStudyprogressAdapter = new StudyProgressRecyclerAdapter(getActivity(), subjects); //ToDO прочитать про onAttach метод, реализовать его
        mRecyclerView.setAdapter(mTStudyprogressAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
