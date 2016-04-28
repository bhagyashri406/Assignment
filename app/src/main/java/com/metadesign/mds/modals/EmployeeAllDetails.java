package com.metadesign.mds.modals;

public class EmployeeAllDetails {

    String Firstname;
    String Lastname;
    String Email;
    String Address;
    String PhoneNumber;
    String Designation;
    String Department;
    String Income;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public EmployeeAllDetails() {
    }

    public EmployeeAllDetails(String Firstname,
                              String Lastname,
                              String Email,
                              String Address,
                              String PhoneNumber,
                              String Designation,
                              String Department,
                              String Income) {

        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Address = Address;
        this.Department = Department;
        this.Designation = Designation;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Income = Income;
    }

}
