package com.techelevator.tenmo.controller;

import org.springframework.web.bind.annotation.RestController;

/*******************************************************************************************************
 * This is where you code any API controllers you may create
********************************************************************************************************/
import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.TransferStatusDAO;
import com.techelevator.tenmo.dao.TransferTypeDAO;
import com.techelevator.tenmo.dao.UserDAO;


@RestController

public class ApiController {
	
	// Define DAO references
	private AccountDAO accountDAO;
	private TransferDAO transferDAO;
	private TransferStatusDAO transferStatusDAO;
	private TransferTypeDAO transferTypeDAO;
	private UserDAO userDAO;
	
	// Constructor
	public ApiController() {
		this.accountDAO = new AccountSqlDAO();
		
		
	}
	
	
}
