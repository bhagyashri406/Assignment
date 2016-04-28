package com.metadesign.mds.modals;

import android.os.Parcel;
import android.os.Parcelable;


public class EmployeeDetail {

  int  Id ;
   String Address ;
   String PhoneNumber ;
   String Designation ;
   String Department;
   String Income ;


 public EmployeeDetail(){
super();
 }

 // constructor
 public EmployeeDetail(int id,
                       String address,
                       String phoneNumber,
                       String designation,
                       String department,
                       String income){
  this.Id= id;
  this.Address = address;
  this.PhoneNumber = phoneNumber;
  this.Designation= designation ;
  this.Department = department;
  this.Income = income;
 }

 // constructor
 public EmployeeDetail(String address,
                       String phoneNumber,
                       String designation,
                       String department,
                       String income){
  this.Address = address;
  this.PhoneNumber = phoneNumber;
  this.Designation= designation ;
  this.Department = department;
  this.Income = income;
 }

 public String getIncome() {
  return Income;
 }

 public void setIncome(String income) {
  Income = income;
 }

 public String getDepartment() {

  return Department;
 }

 public void setDepartment(String department) {
  Department = department;
 }

 public String getDesignation() {

  return Designation;
 }

 public void setDesignation(String designation) {
  Designation = designation;
 }

 public String getPhoneNumber() {

  return PhoneNumber;
 }

 public void setPhoneNumber(String phoneNumber) {
  PhoneNumber = phoneNumber;
 }

 public String getAddress() {

  return Address;
 }

 public void setAddress(String address) {
  Address = address;
 }

 public int getId() {

  return Id;
 }

 public void setId(int id) {
  Id = id;
 }


}
