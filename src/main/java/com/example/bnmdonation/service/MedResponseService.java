package com.example.bnmdonation.service;

import com.example.bnmdonation.model.BloodResponse;
import com.example.bnmdonation.model.MedResponse;
import com.example.bnmdonation.repository.BloodResponseRepo;
import com.example.bnmdonation.repository.MedReqRepo;
import com.example.bnmdonation.repository.MedResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedResponseService {
    @Autowired
    private MedResponseRepo medResponseRepo;

    public MedResponseService(MedResponseRepo medResponseRepo) {
        this.medResponseRepo = medResponseRepo;
    }

    public void addResponse(MedResponse medResponse){
        medResponseRepo.save(medResponse);
    }
    public boolean findMedResponseByUserID(long id,int req_id){
        List<MedResponse> list = medResponseRepo.findMedResponseByUserID(id,req_id);
        if(list.size()==0){
            return false;
        }else return true;
    }
    public List<MedResponse> findUserByRequestID(int id){
        return medResponseRepo.findUserByRequestID(id);
    }
}
