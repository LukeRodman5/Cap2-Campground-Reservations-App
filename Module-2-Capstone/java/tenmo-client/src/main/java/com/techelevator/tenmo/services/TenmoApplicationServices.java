package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.view.ConsoleService;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {
	
	// Hold the main part of the server URL
	private final String BASE_URL;
	
	// Instantiate a restTemplate object and name it callAPI
	private RestTemplate callAPI = new RestTemplate();
	
	// Constructor - sets up the server URL
	public TenmoApplicationServices(String url) {
		BASE_URL = url;
	}
	
	
	// Case 3 - Get account by id
	public Account getAccountById(int id) {
		return callAPI.getForObject(BASE_URL, Account.class);
	}
	
	// Case 4 - List users
	public User[] listUsers() {
		return callAPI.getForObject(BASE_URL, User[].class);
	}
	
	// Case 5 - Get transfers by accountID
	public Transfer[] getTransfersByAccountId(int accountId) {
		return callAPI.getForObject(BASE_URL, Transfer[].class);
	}
	
	// Case 6 - Get transfer by id
	public Transfer getTransferById(int id) {
		return callAPI.getForObject(BASE_URL, Transfer.class);
	}
	

}
