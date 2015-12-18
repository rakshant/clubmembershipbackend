package com.cmm.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserTreasurerBudget;
import com.cmm.spring.rest.repository.BudgetRepository;



@Service
public class BudgetServiceImpl implements BudgetService {
	
	
	@Autowired
	private BudgetRepository  budgetRepository;

	public UserTreasurerBudget allocateBudget(UserTreasurerBudget userTreasurerBudget) {
		
	//	UserTreasurerBudget budget = budgetRepository.findOne("5672a490f236b672e530bb6b");
		
		List<UserTreasurerBudget> list=budgetRepository.findAll();
		if(list.size()!=1)
		{
			
		UserTreasurerBudget budget=new UserTreasurerBudget();
		budget.setIndoor(userTreasurerBudget.getIndoor()+budget.getIndoor());
		budget.setOutdoor(userTreasurerBudget.getOutdoor()+budget.getOutdoor());
		budget.setLeisure(userTreasurerBudget.getLeisure()+budget.getLeisure());
		
		budgetRepository.save(budget);
		return budget;
		}
		else
		{
			UserTreasurerBudget budget=list.get(0);
			budget.setIndoor(userTreasurerBudget.getIndoor()+budget.getIndoor());
			budget.setOutdoor(userTreasurerBudget.getOutdoor()+budget.getOutdoor());
			budget.setLeisure(userTreasurerBudget.getLeisure()+budget.getLeisure());
			budgetRepository.save(budget);
		return budget;
		}
		
		//return budget;
	}

	
	public UserTreasurerBudget getAllocateBudget() {
		List<UserTreasurerBudget> list=budgetRepository.findAll();
		if(list.size()!=1)
			return null;
		else
		{
			UserTreasurerBudget userTreasurerBudget=new UserTreasurerBudget();
			userTreasurerBudget=list.get(0);
			return userTreasurerBudget;
		}
	}
	

}
