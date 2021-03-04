package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public class TransferStatusSqlDAO {

	void updateStatus (String Status, Transfer aTransfer){
		if (Status == "Pending") {
			aTransfer.setStatusId(1);
		}
		else if (Status == "Approved") {
			aTransfer.setStatusId(2);
		
		} else if (Status == "Rejected") {
			aTransfer.setStatusId(3);
		}
	}
}
