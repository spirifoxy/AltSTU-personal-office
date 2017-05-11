package com.tolichp.spirifoxy.altstu_personal_office.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by spirifoxy on 11.05.2017.
 */

public class Lesson implements Parcelable {
    private Calendar startTime;
    private Calendar endTime;
    private String title;
    private String audience;
    private String classType;
    private String info;

    public Lesson() {
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
        title = "";
        audience = "";
        classType = "";
        info = "";
    }

    private Lesson(Parcel in) {
        long msStartTime = in.readLong();
        String tzIdStartTime = in.readString();
        startTime = new GregorianCalendar(TimeZone.getTimeZone(tzIdStartTime));
        startTime.setTimeInMillis(msStartTime);

        long msEndTime = in.readLong();
        String tzIdEndTime = in.readString();
        endTime = new GregorianCalendar(TimeZone.getTimeZone(tzIdEndTime));
        endTime.setTimeInMillis(msEndTime);

        title = in.readString();
        audience = in.readString();
        classType = in.readString();
        info = in.readString();
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(startTime.getTimeInMillis());
        dest.writeString(startTime.getTimeZone().getID());

        dest.writeLong(endTime.getTimeInMillis());
        dest.writeString(endTime.getTimeZone().getID());

        dest.writeString(title);
        dest.writeString(audience);
        dest.writeString(classType);
        dest.writeString(info);
    }
}
