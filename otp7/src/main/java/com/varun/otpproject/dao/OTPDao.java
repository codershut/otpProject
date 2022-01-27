package com.varun.otpproject.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.varun.otpproject.entity.OTPEntity;

//Here we are extending JPA repository

@Repository
public interface OTPDao extends MongoRepository<OTPEntity, String>{

}
