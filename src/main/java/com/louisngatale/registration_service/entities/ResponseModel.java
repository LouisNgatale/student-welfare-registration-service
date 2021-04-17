package com.louisngatale.registration_service.entities;

public class ResponseModel {
    private String jwt;

    public ResponseModel(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
