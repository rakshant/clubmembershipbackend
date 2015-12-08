package com.cmm.spring.rest.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cmm.spring.mongo.collections.UserLogin;

public interface LoginRepository extends MongoRepository<UserLogin, String> {

}
