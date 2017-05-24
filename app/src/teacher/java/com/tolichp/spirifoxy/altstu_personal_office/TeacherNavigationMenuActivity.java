package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class TeacherNavigationMenuActivity extends NavigationMenuActivity {

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch(item.getItemId()) {
            case R.id.nav_timetable:
                fragment = TimetableFragment.newInstance();
//                fragment = TTWeekFragment.newInstance();
//                fragment = TTDayFragment.newInstance();
                break;
            case R.id.nav_messages_t:
                fragment = TeacherMessages.newInstance();
                break;
            case R.id.nav_attendance:
                fragment = AttendanceFragment.newInstance();
                break;
            case R.id.nav_attestation_t:
                fragment = AttestationTeacherFragment.newInstance();
                break;
            case R.id.nav_filling_discipline:
                fragment = FillingDisciplineFragment.newInstance();
                break;
            case R.id.nav_study:
                fragment = StudyFragment.newInstance();
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
