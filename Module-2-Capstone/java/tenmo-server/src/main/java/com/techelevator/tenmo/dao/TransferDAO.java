package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	// Create a transfer of a specific amount to a specific user
	void createTransfer(Transfer newTransfer);
	
	// List all transfers based on user id
	List<Transfer> findTransfersByUserId(Integer userId); 
	
	public List<Transfer> findTransferById(long transferID);
	
	}
