package com.tolichp.spirifoxy.altstu_personal_office.data;

/**
 * Created by Alexandr on 22.05.2017.
 */

public class AttestatedStudent {

    private int number;
    private int attestation1;
    private int attestation2;
    private int attendance1;
    private int attendance2;
    private String FIO;

    public AttestatedStudent(int number, int attestation1, int attestation2, int attendance1, int attendance2, String FIO) {
        this.number = number;
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.attendance1 = attendance1;
        this.attendance2 = attendance2;
        this.FIO = FIO;
    }

    public AttestatedStudent(int number, String FIO) {
        this.number = number;
        this.FIO = FIO;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAttestation1() {
        return attestation1;
    }

    public void setAttestation1(int attestation1) {
        this.attestation1 = attestation1;
    }

    public int getAttestation2() {
        return attestation2;
    }

    public void setAttestation2(int attestation2) {
        this.attestation2 = attestation2;
    }


    public int getAttendance1() {
        return attendance1;
    }

    public void setAttendance1(int attendance1) {
        this.attendance1 = attendance1;
    }


    public int getAttendance2() {
        return attendance2;
    }

    public void setAttendance2(int attendance2) {
        this.attendance2 = attendance2;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
