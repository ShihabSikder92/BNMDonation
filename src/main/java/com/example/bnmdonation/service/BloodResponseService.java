package com.example.bnmdonation.service;

import com.example.bnmdonation.model.BloodResponse;
import com.example.bnmdonation.model.User;
import com.example.bnmdonation.repository.BloodResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodResponseService {
    @Autowired
    private BloodResponseRepo bloodResponseRepo;

    public BloodResponseService(BloodResponseRepo bloodResponseRepo) {
        this.bloodResponseRepo = bloodResponseRepo;
    }

    public void addResponse(BloodResponse bloodResponse){
        bloodResponseRepo.save(bloodResponse);
    }
    public boolean findBloodResponseByUserID(long id,int req_id){
        List<BloodResponse> list = bloodResponseRepo.findBloodResponseByUserID(id,req_id);
        if(list.size()==0){
            return false;
        }else return true;
    }
    public List<BloodResponse> findUserByRequestID(int id){
        List<BloodResponse> list = bloodResponseRepo.findUserByRequestID(id);
        return list;
    }
}
