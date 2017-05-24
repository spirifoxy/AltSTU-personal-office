package com.tolichp.spirifoxy.altstu_personal_office.data;

import java.util.Date;

/**
 * Created by Alexandr on 24.05.2017.
 */
public class StudyItem {

    private String name;
    private int number;
    private Date termdate;
    private Date passdate;
    private int points;


    public StudyItem(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public StudyItem(String name, int number, Date passdate, int points) {
        this.name = name;
        this.number = number;
        this.passdate = passdate;
        this.points = points;
    }

    public StudyItem(String name, int number, Date termdate, Date passdate, int points) {
        this.name = name;
        this.number = number;
        this.termdate = termdate;
        this.passdate = passdate;
        this.points = points;
    }

    public StudyItem(String name, int number, Date termdate) {
        this.name = name;
        this.number = number;
        this.termdate = termdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getTermdate() {
        return termdate;
    }

    public void setTermdate(Date termdate) {
        this.termdate = termdate;
    }

    public Date getPassdate() {
        return passdate;
    }

    public void setPassdate(Date passdate) {
        this.passdate = passdate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
