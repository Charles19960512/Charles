package com.startsmake.StreetDemo.login;


public class User {

    private int id;
    private String username;
    private String userpwd;
    private String sex;
    private int age;
    private int phone;
    private String addr;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUserSex(){return sex;}
    public void setUsersex(String Usersex){this.sex=Usersex;}

    public int getUserage() {
        return age;
    }
    public void setUserage(int Userage) {
        this.age = Userage;
    }

    public int getUserphone() {
        return phone;
    }
    public void setUserphone(int Userphone) {
        this.phone = Userphone;
    }

    public String getUseraddr(){return addr;}
    public void setUseraddr(String Useraddr){this.addr=Useraddr;}

    public String getUserdate(){return date;}
    public void setUserdate(String Userdate){this.date=Userdate;}
}