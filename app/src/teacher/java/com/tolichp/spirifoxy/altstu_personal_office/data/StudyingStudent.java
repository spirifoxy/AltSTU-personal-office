package com.tolichp.spirifoxy.altstu_personal_office.data;


public class StudyingStudent {

    private String fio;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public StudyingStudent(int number, String fio) {
        this.number = number;
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
