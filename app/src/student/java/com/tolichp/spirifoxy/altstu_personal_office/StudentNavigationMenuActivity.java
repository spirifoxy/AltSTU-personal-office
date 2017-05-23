package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class StudentNavigationMenuActivity extends NavigationMenuActivity {

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch(item.getItemId()) {
            case R.id.nav_timetable:
                fragment = TimetableFragment.newInstance();
                break;
            case R.id.nav_messages_st:
                fragment = NewsFragment.newInstance();
                break;
            case R.id.nav_studyprogress:
                fragment = StudyProgressFragment.newInstance();
                break;
            case R.id.nav_sessions:
                fragment = SessionsFragment.newInstance();
                break;
        }

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
