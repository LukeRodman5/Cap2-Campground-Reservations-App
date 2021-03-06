package com.techelevator.tenmo.controller;

import java.util.List;

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
import com.techelevator.tenmo.model.User;


@RestController
//@PreAuthorize("isAuthenticated()")


public class ApiController {
	
	// Define DAO references
	private AccountDAO accountDAO;
	private TransferDAO transferDAO;
	private UserDAO userDAO;
	
	// Constructor - we'll come back to this
	public ApiController(AccountDAO accountDao, TransferDAO transferDao, UserDAO userDao) {
		this.accountDAO = accountDao;
		this.transferDAO = transferDao;
		this.userDAO = userDao;
		// add each dao as argument here and add @Component
	}
	
	/****************************************
	 **** ACCOUNTS
	 *****************************************/
	
	// Get Account Balance
	@RequestMapping(path = "tenmo/balance/{id}", method = RequestMethod.GET)
	public double getBalance(@PathVariable long id) {
		return accountDAO.findBalanceByAccountId(id);
	}
	
	// Increase Account Balance
	//@RequestMapping
	public double increaseBalance(@Valid @RequestBody Transfer transfer, @PathVariable int id) {
		return accountDAO.increaseBalance(id, transfer.getAmount());
	}
	
	// Decrease Account Balance
	//@RequestMapping
	public double decreaseBalance(@Valid @RequestBody Transfer transfer, @PathVariable int id) {
		return accountDAO.decreaseBalance(id, transfer.getAmount());
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
	public List<Transfer> listTransfers(@PathVariable int id) {
		return transferDAO.findTransfersByAccountId(id);
	}
	
	// See details of transfer based on transfer ID
	// @RequestMapping
	public Transfer getTransfer(@PathVariable int id) {
		return transferDAO.findTransferById(id);
	}
	
	/*******************************************************************
	 * USERS
	 ******************************************************************/
	// List all users
	// @RequestMapping
	public List<User> listUsers() {
		return userDAO.findAll();
	}
	
	
	
}
		
	

