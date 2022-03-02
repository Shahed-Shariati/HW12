package model;

import utility.ValidationDigitPhoneNumber;
import utility.ValidationLengthPhoneNumber;

import javax.persistence.*;


public class User {

  private int id;

  private int role;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String address;
  private String userName;
  private String passWord;
public User(){}
  public User(int id, int role, String firstName, String lastName, String phoneNumber, String address, String userName, String passWord) {
   validationPhoneNumber(phoneNumber);
   this.id = id;
   this.role = role;
   this.firstName = firstName;
   this.lastName = lastName;
   this.phoneNumber = phoneNumber;
   this.address = address;
   this.userName = userName;
   this.passWord = passWord;
  }

  public int getId() {
   return id;
  }

  public void setId(int id) {
   this.id = id;
  }

  public int getRole() {
   return role;
  }

  public void setRole(int role) {
   this.role = role;
  }

  public String getFirstName() {
   return firstName;
  }

  public void setFirstName(String firstName) {
   this.firstName = firstName;
  }

  public String getLastName() {
   return lastName;
  }

  public void setLastName(String lastName) {
   this.lastName = lastName;
  }

  public String getPhoneNumber() {
   return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
   this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
   return address;
  }

  public void setAddress(String address) {
   this.address = address;
  }

  public String getUserName() {
   return userName;
  }

  public void setUserName(String userName) {
   this.userName = userName;
  }

  public String getPassWord() {
   return passWord;
  }

  public void setPassWord(String passWord) {
   this.passWord = passWord;
  }
  private void validationPhoneNumber(String phoneNumber){
   char[] nationalCodeArray = phoneNumber.toCharArray();
   if(nationalCodeArray.length == 11) {
    for (char c : nationalCodeArray) {
     if (Character.isDigit(c) && nationalCodeArray[0]=='0') {

     } else {
      throw new ValidationDigitPhoneNumber("phone is not digit");
     }
    }
   }else {
    throw new ValidationLengthPhoneNumber("phone length");
   }
  }
 }
