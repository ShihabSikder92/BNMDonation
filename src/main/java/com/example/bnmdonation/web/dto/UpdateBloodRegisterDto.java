package com.example.bnmdonation.web.dto;

public class UpdateBloodRegisterDto {
    private int id;
    private String nickName;
    private String contact;
    private String bgn;
    private String hospital;
    private String hosLocation;
    private int quantity;

    public UpdateBloodRegisterDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
