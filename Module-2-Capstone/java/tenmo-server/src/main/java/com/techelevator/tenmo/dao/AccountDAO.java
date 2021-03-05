package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	// Find the balance by account id
	Double findBalanceByAccountId(long accountId);
	
	// Increase balance of receiving user
	Double increaseBalance(long accountId, Double amountToTransfer);
	
	// Decrease balance of sending user
	Double decreaseBalance(long accountId, Double amountToTransfer);
	// We would put some kind of error message if the new balance is negative
	void save(Account newAccount);
}
