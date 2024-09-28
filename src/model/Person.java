package model;

public class Person {

    protected String nic = "";
    protected String fName = "";
    protected String lName = "";
    protected String dob = "";
    protected String address = "";
    protected String city = "";
    protected String gender = "";
    protected String telHome = "";
    protected String telMobile = "";

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFname() {
        return fName;
    }

    public void setFname(String fname) {
        this.fName = fname;
    }

    public String getLname() {
        return lName;
    }

    public void setLname(String lname) {
        this.lName = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelHome() {
        return telHome;
    }

    public void setTel_home(String telHome) {
        this.telHome = telHome;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public void setTel_mobile(String telMobile) {
        this.telMobile = telMobile;
    }

}
