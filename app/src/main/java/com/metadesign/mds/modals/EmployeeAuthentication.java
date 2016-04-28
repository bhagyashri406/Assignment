package com.metadesign.mds.modals;


public class EmployeeAuthentication {
   int Id ;
  String  Firstname;
  String  Lastname;
  String  Email;
    String Password;

    public EmployeeAuthentication(){super();}


    public EmployeeAuthentication(int id,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  String password){

        this.Id=id;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email = email;
        this.Password = password;

    }

    public EmployeeAuthentication(String firstname,
                                  String lastname,
                                  String email,
                                  String password){
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email=email;
        this.Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }






}
