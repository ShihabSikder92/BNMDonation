package com.example.bnmdonation.repository;

import com.example.bnmdonation.model.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodReqRepo extends JpaRepository<BloodRequest,Integer> {
    @Query("from BloodRequest where userid = :id")
    public List<BloodRequest> findBloodRequestByUserid(@Param("id")long id);
}
