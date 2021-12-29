package com.example.bnmdonation.repository;

import com.example.bnmdonation.model.BloodResponse;
import com.example.bnmdonation.model.MedResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedResponseRepo extends JpaRepository<MedResponse,Integer> {
    @Query("from MedResponse WHERE user.id = :id AND medRequest.id = :req_id")
    public List<MedResponse> findMedResponseByUserID(@Param("id") long userid,@Param("req_id") int requestid);
    @Query("from MedResponse where medRequest.id= :id")
    public List<MedResponse> findUserByRequestID(@Param("id") int id);
}
