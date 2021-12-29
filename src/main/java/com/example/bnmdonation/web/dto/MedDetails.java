package com.example.bnmdonation.web.dto;

public class MedDetails {
    private int id;
    private String MedName;
    private String company;
    private int quantity;

    public MedDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String medName) {
        MedName = medName;
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

    @Override
    public String toString() {
        return "MedDetails{" +
                "id=" + id +
                ", MedName='" + MedName + '\'' +
                ", company='" + company + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
