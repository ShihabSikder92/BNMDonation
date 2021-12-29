package com.example.bnmdonation.repository;

import com.example.bnmdonation.model.BloodResponse;
import com.example.bnmdonation.model.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import java.util.List;

@Repository
public interface BloodResponseRepo extends JpaRepository<BloodResponse, Integer> {
    @Query("from BloodResponse WHERE user.id = :id AND bloodRequest.id = :req_id")
    public List<BloodResponse> findBloodResponseByUserID(@Param("id") long id,@Param("req_id") int requestid);
    @Query("from BloodResponse where bloodRequest.id = :id")
    public List<BloodResponse> findUserByRequestID(@Param("id") int id);
}
