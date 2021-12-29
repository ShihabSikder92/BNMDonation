package com.example.bnmdonation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "med_response")
public class MedResponse {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "med_request_id")
    private MedRequest medRequest;

    public MedRequest getMedRequest() {
        return medRequest;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMedRequest(MedRequest medRequest) {
        this.medRequest = medRequest;
    }
}
