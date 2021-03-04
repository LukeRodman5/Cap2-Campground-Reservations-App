package com.techelevator.tenmo.dao;

public interface AccountDAO {
	
	// Find the balance by account id
	Double findBalanceByAccountId(Integer accountId);
	
	// Increase balance of receiving user
	Double increaseBalance(Integer accountId, Double amountToTransfer);
	
	// Decrease balance of sending user
	Double decreaseBalance(Integer accountId, Double amountToTransfer);
	// We would put some kind of error message if the new balance is negative

}
