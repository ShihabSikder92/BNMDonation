package com.example.bnmdonation.web.dto;

public class MedRegisterDto {
    private String name;
    private String contact;
    private String location;
    private String medName;
    private String company;
    private int quantity;

    public MedRegisterDto() {
    }

    public MedRegisterDto(String name, String contact, String location, String medName, String company, int quantity) {
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.medName = medName;
        this.company = company;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
