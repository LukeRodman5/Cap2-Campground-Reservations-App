package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public class TransferTypeSqlDAO {

	public void setTransferType (String Type, Transfer aTransfer){
		if (Type == "Send") {
		aTransfer.setTypeId(2);
		
		} else if (Type == "Request") {
			aTransfer.setTypeId(1);
		}
	}
}
