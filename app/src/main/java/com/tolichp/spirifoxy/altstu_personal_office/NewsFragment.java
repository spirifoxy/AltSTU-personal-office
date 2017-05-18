package com.tolichp.spirifoxy.altstu_personal_office;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private static final String TAG = NewsFragment.class.getSimpleName();
    private ListView listView;
    private ViewPagerAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;

    @SuppressLint("NewApi")

    public static NewsFragment newInstance() {//String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setContentView(R.layout.fragment_news);
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        fragmentsList = new ArrayList<>();
        fragmentsList.add(new NewsMessagesFragment());
        fragmentsList.add(new NewsNotificationsFragment());

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        feedItems = new ArrayList<>();

        String[] titlesList = getResources().getStringArray(R.array.info_titles);
        listAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentsList, titlesList);
        viewPager.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}