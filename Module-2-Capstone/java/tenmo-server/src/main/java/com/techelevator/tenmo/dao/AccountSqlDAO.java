package com.techelevator.tenmo.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.model.Account;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountSqlDAO implements AccountDAO {
	
	private JdbcTemplate jdbcTemplate;	
	
	public AccountSqlDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Double findBalanceByAccountId(long accountId) {
		String sql = "select balance from accounts where account_id = ?";
		return jdbcTemplate.queryForObject(sql, Double.class, accountId);
	}
	
	@Override
	public Double increaseBalance(long accountId, Double amountToTransfer) {
		String sql = "select balance from accounts where account_id = ?";
		Double initialBalance = jdbcTemplate.queryForObject(sql, Double.class, accountId);
		return initialBalance + amountToTransfer;
	}
	
	@Override
	public void save(Account newAccount) {
		String sqlInsertAccount = "INSERT INTO account(account_id, user_id, balance)" + "VALUES(?,?,?)";
		jdbcTemplate.update(sqlInsertAccount, newAccount.getId(), newAccount.getUserId(), newAccount.getBalance());
	}
	
	@Override	
	public Double decreaseBalance(long accountId, Double amountToTransfer) {
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
