package model;

import utility.ValidationDigitNationalCode;
import utility.ValidationDigitPhoneNumber;
import utility.ValidationLengthNationalCode;
import utility.ValidationLengthPhoneNumber;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;
    private String address;
    private String userName;
    private String passWord;
    private int role;

    public User(){

    }

    public User(String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord, int role) {
        validationNationalCode(nationalCode);
        validationPhoneNumber(phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public User(int id, String firstName, String lastName, String nationalCode, int role) {
        validationNationalCode(nationalCode);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.role = role;
    }

    public User(int id, int role, String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord) {
        validationNationalCode(nationalCode);
        validationPhoneNumber(phoneNumber);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
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

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
    private void validationNationalCode(String nationalCode){
        char[] nationalCodeArray = nationalCode.toCharArray();
        if(nationalCodeArray.length == 10) {
            for (char c : nationalCodeArray) {
                if (Character.isDigit(c)) {

                } else {
                    throw new ValidationDigitNationalCode("");
                }
            }
        }else {
            throw new ValidationLengthNationalCode("the length of national code is wrong");
        }
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
