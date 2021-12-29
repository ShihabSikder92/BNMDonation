package com.example.bnmdonation.web.dto;

public class BloodRegisterDto {
    private String nickName;
    private String contact;
    private String bgn;
    private String hospital;
    private String hosLocation;
    private int quantity;
    public BloodRegisterDto() {

    }

    public BloodRegisterDto(String nickName, String contact, String bgn, String hospital, String hosLocation, int quantity) {
        this.nickName = nickName;
        this.contact = contact;
        this.bgn = bgn;
        this.hospital = hospital;
        this.hosLocation = hosLocation;
        this.quantity = quantity;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBgn() {
        return bgn;
    }

    public void setBgn(String bgn) {
        this.bgn = bgn;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHosLocation() {
        return hosLocation;
    }

    public void setHosLocation(String hosLocation) {
        this.hosLocation = hosLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BloodRegisterDto{" +
                "nickName='" + nickName + '\'' +
                ", contact='" + contact + '\'' +
                ", bgn='" + bgn + '\'' +
                ", hospital='" + hospital + '\'' +
                ", hosLocation='" + hosLocation + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
