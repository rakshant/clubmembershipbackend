package com.cmm.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.mongo.collections.UserTreasurerBudget;
import com.cmm.spring.rest.repository.BudgetRepository;



@Service
public class BudgetServiceImpl implements BudgetService {
	
	
	@Autowired
	private BudgetRepository  budgetRepository;

	public UserTreasurerBudget allocateBudget(UserTreasurerBudget userTreasurerBudget) {
		
		UserTreasurerBudget budget = budgetRepository.findOne("5672d668da6e49c6bb31c16a");
		
		budget.setIndoor(userTreasurerBudget.getIndoor()+budget.getIndoor());
		budget.setOutdoor(userTreasurerBudget.getOutdoor()+budget.getOutdoor());
		budget.setLeisure(userTreasurerBudget.getLeisure()+budget.getLeisure());
		
		budgetRepository.save(budget);

		
		return budget;
	}

	
	
	

}
