package com.techelevator.tenmo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************************************************
 * This is where you code any API controllers you may create
********************************************************************************************************/
import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;


@RestController
@PreAuthorize("isAuthenticated()")

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
	}
	
	/****************************************
	 **** ACCOUNTS
	 *****************************************/
	
	/**
	 * Return an account by its id
	 * 
	 * @param id
	 * @return a single account
	 */
	@RequestMapping(path = "/tenmo/account/{id}", method = RequestMethod.GET)
	public Account getAccount(@PathVariable int id) {
		return accountDAO.findAccountById(id);
	}
	
	/**
	 * Get an account balance by its id
	 * @param accountId
	 * @return account balance
	 */
	@RequestMapping(path = "/tenmo/account/{id}/balance", method = RequestMethod.GET)
	public double getBalance(@PathVariable long id) {
		return accountDAO.findBalanceByAccountId(id);
	}
	
	/**
	 * Increase balance of receiving account
	 * 
	 * @param accountId
	 * @param amountToTransfer
	 * 
	 * @return updated account balance
	 */
	// Doesn't work yet
	//@RequestMapping(path = "/tenmo/account/{id}/balance", method = RequestMethod.PUT)
	public double increaseBalance(@Valid @RequestBody Account account, Double amountToTransfer) {
		return accountDAO.increaseBalance(account, amountToTransfer);
	}
	
	/**
	 * Decrease balance of sending account
	 * 
	 * @param accountId
	 * @param amountToTransfer
	 * 
	 * @return updated account balance
	 */
	// Doesn't work yet
	//@RequestMapping(path = "/tenmo/account/{id}/balance", method = RequestMethod.PUT)
	public double decreaseBalance(@Valid @RequestBody Account account, Double amountToTransfer) {
		return accountDAO.decreaseBalance(account, amountToTransfer);
	}
	
	/*********************************************
	 *** TRANSFERS
	 ************************************************/
	/*
	 * Create a new transfer
	 * 
	 * @param newTransfer
	 */
	// Doesn't work yet
	@RequestMapping(path = "/tenmo/transfer", method = RequestMethod.POST)
	public void addTransfer(@Valid @RequestBody Transfer transfer) {
		transferDAO.save(transfer);
	}
	
	/*
	 * See details of transfer based on id
	 * 
	 * @param id
	 * @return transfer
	 */
	
	@RequestMapping(path = "/tenmo/transfer/{id}", method = RequestMethod.GET)
	public Transfer getTransfer(@PathVariable int id) {
			return transferDAO.findTransferById(id);
	}
	
	
	/*
	 * See transfers user has sent or received based on account ID
	 * 
	 * @param accountId
	 * @return list of transfers
	 */
	// I don't know how to do this one
	@RequestMapping(path = "/tenmo/transfer", method = RequestMethod.GET)
	public List<Transfer> listTransfers(int accountId) {
		return transferDAO.findTransfersByAccountId(accountId);
	}

	/*******************************************************************
	 * USERS
	 ******************************************************************/
	/*
	 * List all users
	 * 
	 * @return list of users
	 */
	@RequestMapping(path = "/tenmo/user", method = RequestMethod.GET)
	public List<User> listUsers() {
		return userDAO.findAll();
	}
	
	/*
	 * Find userId by username
	 * 
	 * @param username
	 * @return userId
	 */
	// I don't know how to do this one
	@RequestMapping(path = "/tenmo/user", method = RequestMethod.GET)
	public int findUserId(String username) {
		return userDAO.findIdByUsername(username);
	}
	
	
	
}
		
	

