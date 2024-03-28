package com.example.optimumhealth;

public class Employeed {
    private String drid,drtype,drname,drexp,drmob,drfees;

    public Employeed() {
    }

    public Employeed(String drid, String drtype, String drname, String drexp, String drmob,String drfees) {
        this.drid = drid;
        this.drtype = drtype;
        this.drname = drname;
        this.drexp = drexp;
        this.drmob = drmob;
        this.drfees = drfees;

    }

    public String getDrId() {
        return drid;
    }

    public void setDrId(String drid) {
        this.drid = drid;
    }

    public String getDrType() {
        return drtype;
    }

    public void setDrType(String drtype) {
        this.drtype = drtype;
    }

    public String getDrName() {
        return drname;
    }

    public void setDrName(String drname) {
        this.drname = drname;
    }

    public String getDrExp() {
        return drexp;
    }

    public void setDrExp(String drexp) {
        this.drexp = drexp;
    }

    public String getDrMob() {
        return drmob;
    }

    public void setDrMob(String drmob) {
        this.drmob = drmob;
    }

    public String getDrFees() {
        return drfees;
    }

    public void setDrFees(String drfees) {
        this.drfees = drfees;
    }


}

