package com.techelevator.tenmo.models;

public class Account {
	
	private Long id; 
	private int userId;
	private Double balance = 1000.00;
	
	/* @return id */
	public long getId() {
		return id;}
	
	/* @param id to set */
	public void setId(long id) {
		this.id = id;}
	
	/* @return userId */
	public int getUserId() {
		return userId;}
	
	/* @param userId to set */
	public void setUserId(Integer userId) {
		this.userId = userId;}
	
	/* @return balance*/
	public Double getBalance() {
		return balance;}
	
	/* @param balance to set */
	public void setBalance(Double balance) {
		this.balance = balance;}
	
@Override
	public String toString() {
	    return  "\n--------------------------------------------" +
	            "\n Your Account Balance Information" +
	            "\n--------------------------------------------" +
	            "\n Account Id: " + id +
	            "\n User Id: " + userId +
	            "\n Balance: " + balance;
	    }
}