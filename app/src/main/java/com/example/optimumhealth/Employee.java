package com.example.optimumhealth;

public class Employee {
    private String bookid,usern,userp,doctortype,doctorname,bookdate,booktime;

    public Employee() {
    }

    public Employee(String bookid, String usern, String userp, String doctortype, String doctorname,String bookdate, String booktime) {
        this.bookid = bookid;
        this.usern = usern;
        this.userp = userp;
        this.doctortype = doctortype;
        this.doctorname = doctorname;
        this.bookdate = bookdate;
        this.booktime = booktime;

    }

    public String getbookId() {
        return bookid;
    }

    public void setBookId(String bookid) {
        this.bookid = bookid;
    }

    public String getUserName() {
        return usern;
    }

    public void setUserName(String usern) {
        this.usern = usern;
    }

    public String getUserP() {
        return userp;
    }

    public void setUserP(String userp) {
        this.userp = userp;
    }

    public String getDoctorName() {
        return doctorname;
    }

    public void setDoctorName(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctorType() {
        return doctortype;
    }

    public void setDoctorType(String doctortype) {
        this.doctortype = doctortype;
    }

    public String getbookDate() {
        return bookdate;
    }

    public void setbookdate(String bookdate) {
        this.bookdate = bookdate;
    }

    public String getbookTime() {
        return booktime;
    }

    public void setbooktime(String booktime) {
        this.booktime = booktime;
    }
}
