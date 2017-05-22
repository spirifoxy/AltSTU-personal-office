package com.tolichp.spirifoxy.altstu_personal_office;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class TeacherNavigationMenuActivity extends NavigationMenuActivity {


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_messages_t) {
            fragment = TeacherMessages.newInstance();
        } else if (id == R.id.nav_attendance) {
            fragment = AttendanceFragment.newInstance();
        }
        else if (id == R.id.nav_attestation_t){
            fragment = AttestationTeacherFragment.newInstance();
        }
        else if (id == R.id.nav_timetable){
            fragment = TimetableFragment.newInstance();
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
