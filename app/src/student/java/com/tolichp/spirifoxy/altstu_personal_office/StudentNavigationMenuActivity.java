package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class StudentNavigationMenuActivity extends NavigationMenuActivity {


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
////        TimetableFragment fragment = TimetableFragment.newInstance();
////        NewsFragment fragment = new NewsFragment();
////        TimetableFragment fragment = new TimetableFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.content_frame, fragment);
//        transaction.replace(R.id.content_frame, fragment);
//        transaction.commit();

        if (id == R.id.nav_timetable) {
            fragment = TimetableFragment.newInstance();
        } else if (id == R.id.nav_messages_st) {
            fragment = NewsFragment.newInstance();
        } else if (id == R.id.nav_slideshow) {
            fragment = StudyProgressFragment.newInstance();
        } else if (id == R.id.nav_manage) {
            fragment = SessionsFragment.newInstance();
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        } else if (id == R.id.nav_test) {
//            fragment = SubjectFragment.newInstance();
        }/* else if (id == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }*/


        FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(this);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
