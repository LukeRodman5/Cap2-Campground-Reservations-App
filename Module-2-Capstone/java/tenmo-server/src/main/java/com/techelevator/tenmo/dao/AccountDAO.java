package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	public void save(Account newAccount);	// Create/Add an Account to the table
	
	// Search methods to Retrieve data from table
	public Account findAccountById(long AccountId);
	public List<Account> findAccountsByUserId(int userId);
	public Double findBalanceByAccountId(long accountId);
	
	
	// Update methods
	// Increase balance of receiving user
	public Double increaseBalance(Account account, Double amountToTransfer);

	// Decrease balance of sending user
	public Double decreaseBalance(Account account, Double amountToTransfer);
	
	
	// Delete a row from the table using the id passed
	public void delete(long id);
	
}
