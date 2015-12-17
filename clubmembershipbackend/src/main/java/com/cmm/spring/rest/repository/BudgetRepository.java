package com.cmm.spring.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserTreasurerBudget;

public interface BudgetRepository  extends MongoRepository<UserTreasurerBudget, String> {

}
