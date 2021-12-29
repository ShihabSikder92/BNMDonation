package com.example.bnmdonation.service;

import com.example.bnmdonation.model.BloodRequest;
import com.example.bnmdonation.model.BloodResponse;
import com.example.bnmdonation.repository.BloodReqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodReqService {
    @Autowired
    private BloodReqRepo bloodReqRepo;

    public void addbloodReq(BloodRequest bloodRequest){
        bloodReqRepo.save(bloodRequest);
    }
    public List<BloodRequest> getallBloodReq(){
        return bloodReqRepo.findAll();
    }
    public BloodRequest getRequestbyID(int id){
        Optional<BloodRequest> req = bloodReqRepo.findById(id);
        if(req.isPresent()){
            return req.get();
        }
        return null;
    }
    public List<BloodRequest> getRequestByUserID(Long id){
        return bloodReqRepo.findBloodRequestByUserid(id);
    }
    public void delete(int id){
        bloodReqRepo.deleteById(id);
    }
}
