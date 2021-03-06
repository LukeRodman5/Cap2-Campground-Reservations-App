package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	// Create a transfer of a specific amount to a specific user
	Transfer createTransfer(Transfer newTransfer);
	
	// List all transfers based on account id
	List<Transfer> findTransfersByAccountId(Integer accountId); 
	
	Transfer findTransferById(long transferID);
	
	}
