package com.example.bnmdonation.repository;

import com.example.bnmdonation.model.BloodRequest;
import com.example.bnmdonation.model.MedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedReqRepo extends JpaRepository<MedRequest,Integer> {
    @Query("from MedRequest where userid = :id")
    public List <MedRequest> findMedRequestByUserid(@Param("id")long id);
}
