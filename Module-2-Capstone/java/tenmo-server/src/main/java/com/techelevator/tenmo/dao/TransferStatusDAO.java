package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferStatusDAO {
	
	void updateStatus (String Status, Transfer aTransfer);
	
	// Transfer includes the User IDs of the from and to users and the amount of TE Bucks
	

}
