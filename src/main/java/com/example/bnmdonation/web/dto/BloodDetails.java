package com.example.bnmdonation.web.dto;

public class BloodDetails {
    private int id;
    private String bgn;
    private String  hospital;
    private String hosLocation;
    private int quantity;

    public BloodDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
