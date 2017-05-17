package com.tolichp.spirifoxy.altstu_personal_office.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter { //PagerAdapter {//FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;
    private String[] titlesList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList, String[] titlesList) { //(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
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