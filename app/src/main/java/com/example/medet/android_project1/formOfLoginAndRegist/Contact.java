package com.example.medet.android_project1.formOfLoginAndRegist;


public class Contact {
    int id;
    String email,uname,pass;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUname(String uname){
        this.uname  = uname;
    }
    public String getUname(){
        return this.uname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getPass(){
        return this.pass;
    }
}
