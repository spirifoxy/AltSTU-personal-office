package com.tolichp.spirifoxy.altstu_personal_office.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class Day implements Parcelable {
    private String name;
    private ArrayList<Lesson> lessons;


    public Day(String name, int testNumber) {
        this.name = name;
        lessons = new ArrayList<>();

        lessons.add(new Lesson()); //TODO test data, delete it
    }

    private Day(Parcel in) {
        name = in.readString();
        //shortName = in.readString();
        lessons = in.createTypedArrayList(Lesson.CREATOR);
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        //dest.writeString(shortName);
        dest.writeTypedList(lessons);
    }

    /*public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }*/
}
