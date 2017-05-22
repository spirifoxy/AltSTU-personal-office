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


import java.util.ArrayList;

public class GroupSettingsFragment extends Fragment {

    /*private ScrollerViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private PagerAdapter weekPagerAdapter;*/


    public static GroupSettingsFragment newInstance() {//String param1, String param2) {
        GroupSettingsFragment fragment = new GroupSettingsFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //        setContentView(R.layout.fragment_studyprogress);
        View view = inflater.inflate(R.layout.fragment_groupsettings, container, false);


        Fragment fragment = ControlPointsListFragment.newInstance();

//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_groupsettings, fragment);
        transaction.commit();

        return view;


    }
}
