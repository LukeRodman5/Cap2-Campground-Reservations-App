package com.techelevator.tenmo.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.techelevator.tenmo.model.Transfer;


@RestController
//@PreAuthorize("isAuthenticated()")


public class ApiController {
	
	// Define DAO references
	private AccountDAO accountDAO;
	private TransferDAO transferDAO;
	private TransferStatusDAO transferStatusDAO;
	private TransferTypeDAO transferTypeDAO;
	private UserDAO userDAO;
	
	// Constructor - we'll come back to this
	public ApiController(AccountDAO accountDao, TransferDAO transferDao) {
		this.accountDAO = accountDao;
		this.transferDAO = transferDao;
		// add each dao as argument here and add @Component
	}
	
	/****************************************
	 **** ACCOUNTS
	 *****************************************/
	
	// Get Account Balance
	@RequestMapping(path = "/tenmo/balance/{id}", method = RequestMethod.GET)
	public double getBalance(@PathVariable long id) {
		return accountDAO.findBalanceByAccountId(id);
	}
	
	
	
	
	
	
	
	/*********************************************
	 *** TRANSFERS
	 ************************************************/
	// Send a transfer of a specific amount to a registered user
	//@PreAuthorize("permitAll()")
	@RequestMapping(path = "tenmo/transfers", method = RequestMethod.POST)
	public Transfer addTransfer(@Valid @RequestBody Transfer transfer) {
		return transferDAO.createTransfer(transfer);
	}
	
	// See transfers user has sent or received (based on user ID)
	//@RequestMapping(path = "/tenmo/")
	
	// See details of transfer based on transfer ID
	
	/*******************************************************************
	 * USERS
	 ******************************************************************/
	
	
}
		
	

