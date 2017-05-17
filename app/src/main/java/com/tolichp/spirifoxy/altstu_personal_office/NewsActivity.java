package com.tolichp.spirifoxy.altstu_personal_office;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.tolichp.spirifoxy.altstu_personal_office.adapter.ViewPagerAdapter;
import com.tolichp.spirifoxy.altstu_personal_office.data.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private static final String TAG = NewsActivity.class.getSimpleName();
    private ListView listView;
    private ViewPagerAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = (ListView) findViewById(R.id.list);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        fragmentsList = new ArrayList<>();
        fragmentsList.add(new NewsMessagesFragment());
        fragmentsList.add(new NewsNotificationsFragment());

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        feedItems = new ArrayList<>();

        String[] titlesList = getResources().getStringArray(R.array.info_titles);
        listAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentsList, titlesList);
        viewPager.setAdapter(listAdapter);



    }
}