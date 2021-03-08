package com.techelevator.tenmo.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.model.Account;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountSqlDAO implements AccountDAO {
	
	private  JdbcTemplate jdbcTemplate;	
	
	public   AccountSqlDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);}
	
@Override
	public void save(Account newAccount) {
		   String sqlInsertAccount = "INSERT INTO account(account_id, user_id, balance) " + "VALUES(?,?,?)";
		   newAccount.setId(getNextAccountId());
		   jdbcTemplate.update(sqlInsertAccount,newAccount.getId(),newAccount.getUserId(),newAccount.getBalance());}

@Override
	public Account findAccountById(long id) {
		   Account theAccount = null;
		   String sqlFindAccountById = "SELECT * " + " FROM accounts " +	" WHERE account_id = ?";
		   SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAccountById, id);
		if 		(results.next()) {
				theAccount = mapRowToAccount(results);}
		return  theAccount;}

@Override
	public  List<Account> findAccountsByUserId(int userId) {
			ArrayList<Account> accounts = new ArrayList<>();
			String sqlFindAccountByUserId = "SELECT * " + " FROM accounts " + " WHERE user_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAccountByUserId, userId);
		if         (results.next()) {
			Account theAccount = mapRowToAccount(results);
			   		accounts.add(theAccount);}
		return 		accounts;}

@Override
	public Double findBalanceByAccountId(long accountId) {
		   String sqlFindBalanceByAccountId = "SELECT balance " + " FROM accounts " + " WHERE account_id = ?";
		return jdbcTemplate.queryForObject(sqlFindBalanceByAccountId, Double.class, accountId);}

@Override
	public  Double increaseBalance(Account account, Double amountToTransfer) {
			String sqlIncreaseBalance = "SELECT increased balance FROM accounts WHERE account_id = ?";
			Double initialBalance = jdbcTemplate.queryForObject(sqlIncreaseBalance, Double.class, account.getId());
			Double updatedBalance = initialBalance + amountToTransfer;
			account.setBalance(updatedBalance);
			String sqlUpdate = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			jdbcTemplate.update(sqlUpdate, updatedBalance, account.getId());
		return 		updatedBalance;}

@Override	
	public  Double decreaseBalance(Account account, Double amountToTransfer) {
			String sqlDecreaseBalance = "SELECT decreased balance from accounts where account_id = ?";
			Double initialBalance = jdbcTemplate.queryForObject(sqlDecreaseBalance, Double.class, account.getId());
			Double updatedBalance = initialBalance - amountToTransfer;
		if        (updatedBalance < 0) {
			return initialBalance;
				} else {
			account.setBalance(updatedBalance);
			String sqlUpdate = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			jdbcTemplate.update(sqlUpdate, updatedBalance, account.getId());
		return 		updatedBalance;}}

@Override
	public void delete(long id) {
			String deleteAnAccount = "DELETE from accounts WHERE account_id = ?";
			jdbcTemplate.update(deleteAnAccount, id);}
	
	private long getNextAccountId() {
			SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_account_id')");
		if        (nextIdResult.next()) {
			return nextIdResult.getLong(1);
				} else {
					throw new RuntimeException("Something went wrong while getting an id for the new account");}}
	
	private Account mapRowToAccount(SqlRowSet results) {
			Account theAccount;
					theAccount = new Account();
					theAccount.setId(results.getLong("account_id"));
					theAccount.setUserId(results.getInt("user_id"));
					theAccount.setBalance(results.getDouble("balance"));
		return      theAccount;}
	}