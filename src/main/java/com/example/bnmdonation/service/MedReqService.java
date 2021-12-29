package com.example.bnmdonation.service;

import com.example.bnmdonation.model.MedRequest;
import com.example.bnmdonation.repository.MedReqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedReqService {
    @Autowired
    private MedReqRepo medReqRepo;

    public void addmedReq(MedRequest medRequest){
        medReqRepo.save(medRequest);
    }
    public List<MedRequest> getallMedReq(){
        return medReqRepo.findAll();
    }
    public MedRequest getRequestbyID(int id){
        Optional<MedRequest> list = medReqRepo.findById(id);
        if(list.isPresent()){
            return list.get();
        }
        return null;
    }
    public List<MedRequest> getRequestByUserID(Long id){
        return medReqRepo.findMedRequestByUserid(id);
    }

    public void delete(int id) {
        medReqRepo.deleteById(id);
    }
}
