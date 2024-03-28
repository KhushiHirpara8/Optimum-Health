package com.example.optimumhealth;

public class Employeen {
    private String nurseid,nursen;

    public Employeen() {
    }

    public Employeen(String nurseid, String nursen) {
        this.nurseid = nurseid;
        this.nursen = nursen;
    }

    public String getnurseId() {
        return nurseid;
    }

    public void setNurseId(String nurseid) {
        this.nurseid = nurseid;
    }

    public String getNurseName() {
        return nursen;
    }

    public void setNurseName(String nursen) {
        this.nursen = nursen;
    }
}
