package com.example.bnmdonation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Med_Request")
public class MedRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "nick_name")
    private String name;
    private int quantity;
    private String location;
    private String company;
    private String contact;
    @Column(name = "med_name")
    private String medName;
    private long userid;

    @OneToMany(targetEntity = MedResponse.class, mappedBy = "medRequest",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MedResponse> medResponses;

    public MedRequest() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "MedRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                ", medName='" + medName + '\'' +
                '}';
    }

    public List<MedResponse> getMedResponses() {
        return medResponses;
    }

    public void setMedResponses(List<MedResponse> medResponses) {
        this.medResponses = medResponses;
    }
}
