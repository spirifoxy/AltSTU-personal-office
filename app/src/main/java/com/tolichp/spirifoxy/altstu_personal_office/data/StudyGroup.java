package com.tolichp.spirifoxy.altstu_personal_office.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexandr on 04.06.2017.
 */

public class StudyGroup implements Parcelable {

    private String name1;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    private String name2;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public StudyGroup() {
        name1 = "PI";
        name2 = "31";
    }

    public static final Creator<StudyGroup> CREATOR = new Creator<StudyGroup>() {
        @Override
        public StudyGroup createFromParcel(Parcel in) {
            return new StudyGroup();
        }

        @Override
        public StudyGroup[] newArray(int size) {
            return new StudyGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}