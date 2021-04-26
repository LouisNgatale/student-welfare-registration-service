package com.louisngatale.registration_service.entities;

public class AdminModel {
    private String fullName;
    private String registrationNumber;
    private String gender;
    private String loginId;
    private String password;
    private Long phoneNumber;

    public AdminModel() {
    }

    public AdminModel(String fullName, String registrationNumber, String gender, String loginId, String password, Long phoneNumber) {
        this.fullName = fullName;
        this.registrationNumber = registrationNumber;
        this.gender = gender;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
