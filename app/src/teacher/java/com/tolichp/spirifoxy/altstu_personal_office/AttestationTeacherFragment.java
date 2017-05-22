package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AttestationTeacherFragment extends android.support.v4.app.Fragment {

    public static AttestationTeacherFragment newInstance() {//String param1, String param2) {
        AttestationTeacherFragment fragment = new AttestationTeacherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_attestation_teacher, container, false);

        Fragment fragment = AttestationListFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_attestation, fragment);
        transaction.commit();

        return view;
    }
}
