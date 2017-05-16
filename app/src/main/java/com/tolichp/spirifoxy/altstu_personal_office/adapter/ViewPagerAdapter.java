package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.tolichp.spirifoxy.altstu_personal_office.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;
    private String[] titlesList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList, String[] titlesList) {
        super(fm);
        this.fragmentsList = fragmentsList;
        this.titlesList = titlesList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList != null
                ? fragmentsList.get(position)
                : null;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

        @Override
    public CharSequence getPageTitle(int position) {
        return titlesList[position];
    }
}