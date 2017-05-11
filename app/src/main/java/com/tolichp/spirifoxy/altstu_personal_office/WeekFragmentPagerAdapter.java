package com.tolichp.spirifoxy.altstu_personal_office;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeekFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;

    public WeekFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList != null
                ? fragmentsList.get(position)
                : null;
//        return TimetableFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
//        return PAGE_COUNT;
    }

}
