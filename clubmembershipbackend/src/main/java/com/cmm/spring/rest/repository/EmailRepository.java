package com.cmm.spring.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cmm.spring.mongo.collections.UserEmail;


public interface EmailRepository extends MongoRepository<UserEmail, String> {

}
