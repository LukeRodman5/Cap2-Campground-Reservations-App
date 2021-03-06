package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.view.ConsoleService;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {
	
	private final String BASE_URL;
	private final RestTemplate restTemplate = new RestTemplate();
	
	public TenmoApplicationServices(String url) {
		BASE_URL = url;
	}
	
	//public Transfer addTransfer(String newTransfer) {
		
	//}
	
	//public Transfer updateTransfer() {
		
	//}
	
	//public void deleteTransfer(int id) {
		
	//}
	
	
	
	

		
}
