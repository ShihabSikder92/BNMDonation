package com.example.bnmdonation.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "blood_request")
public class BloodRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nick_name")
    private String nickName;
    private String contact;
    private String bgn;
    private String hospital;
    @Column(name = "hos_Location")
    private String hosLocation;
    private int quantity;
    private long userid;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToMany(targetEntity = BloodResponse.class, mappedBy = "bloodRequest",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List <BloodResponse> bloodResponses;

    public BloodRequest() {
        super();
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
    public List<BloodResponse> getBloodResponses() {
        return bloodResponses;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setBloodResponses(List<BloodResponse> bloodResponses) {
        this.bloodResponses = bloodResponses;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "BloodRequest{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", contact='" + contact + '\'' +
                ", bgn='" + bgn + '\'' +
                ", hospital='" + hospital + '\'' +
                ", hosLocation='" + hosLocation + '\'' +
                ", quantity=" + quantity +
                ", userid=" + userid +
                ", bloodResponses=" + bloodResponses +
                '}';
    }
}
