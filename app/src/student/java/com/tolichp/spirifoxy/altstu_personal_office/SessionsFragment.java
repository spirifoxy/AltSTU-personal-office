package com.tolichp.spirifoxy.altstu_personal_office;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class SessionsFragment extends Fragment {
    private static final String TAG = SessionsFragment.class.getSimpleName();
    private ListView listView;
    private ViewPagerAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;

    @SuppressLint("NewApi")

    public static SessionsFragment newInstance() {//String param1, String param2) {
        SessionsFragment fragment = new SessionsFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.pager2);
        fragmentsList = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            fragmentsList.add(new SessionsSemFragment());

        PagerTabStrip pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pager_header2);
        pagerTabStrip.setDrawFullUnderline(true);


                feedItems = new ArrayList<>();

        String[] titlesList = getResources().getStringArray(R.array.semesters_titles);
        listAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentsList, titlesList);
        viewPager.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}