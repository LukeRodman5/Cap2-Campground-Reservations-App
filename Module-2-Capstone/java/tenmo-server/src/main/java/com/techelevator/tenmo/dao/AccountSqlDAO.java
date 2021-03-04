package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountSqlDAO implements AccountDAO {
	
	private static final double STARTING_BALANCE = 1000;
	private JdbcTemplate jdbcTemplate;
	
	public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Double findBalanceByAccountId(Integer accountId) {
		//Double theBalance = 0.0;
		String sql = "select balance from accounts where account_id = ?";
		
		return jdbcTemplate.queryForObject(sql, Double.class, accountId);
	}
	
	public Double increaseBalance(Integer accountId, Double amountToTransfer) {
		String sql = "select balance from accounts where account_id = ?";
		
		Double initialBalance = jdbcTemplate.queryForObject(sql, Double.class, accountId);
		
		return initialBalance + amountToTransfer;
	}
	
	public Double decreaseBalance(Integer accountId, Double amountToTransfer) {
		String sql = "select balance from accounts where account_id = ?";
		
		Double initialBalance = jdbcTemplate.queryForObject(sql, Double.class, accountId);
		
		Double newBalance = initialBalance - amountToTransfer;
		
		if (newBalance < 0) {
			//System.out.println("Balance is negative. Transaction failed.");
			// do we need to call an error here?
			//throw new BalanceBelowZeroException();
			
			// Use boolean with DTO
			// Simpler way is just keeping the balance as is
			return initialBalance;
		} else {
			return newBalance;
		}
	}
	
}
