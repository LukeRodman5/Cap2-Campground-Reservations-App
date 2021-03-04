package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.model.Account;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDAOTest {
	private static SingleConnectionDataSource dataSource;
    private AccountSqlDAO dao; 
    @BeforeClass
    public static void setupDataSource() {
                   dataSource = new SingleConnectionDataSource();
                   dataSource.setUrl("jdbc:postgresql://localhost:8080");
                   dataSource.setUsername("postgres");
                   dataSource.setPassword("postgres1");
                   /* The following line disables auto-commit for connections
                    * returned by this DataSource. This allows us to roll-back
                    * any changes after each test */
                   dataSource.setAutoCommit(false);
    }
    @AfterClass
    public static void closeDataSource() throws SQLException {
                   dataSource.destroy();
    }
    @Before
    public void setup() {
    		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    		dao = new AccountSqlDAO(dataSource);
    }
    @After
    public void rollback() throws SQLException {
                   dataSource.getConnection().rollback();
    }
    @Test
    public void returnsBalanceByAccountId() {
    	Account theAccount = getAccount(1,2,3.0);
    	dao.save(theAccount);
    	Double savedBalance = dao.findBalanceByAccountId(theAccount.getId());
    	assertEquals(theAccount.getBalance(),savedBalance);
    	assertNotNull(savedBalance);
    }
    @Test
    public void returnsIncreaseBalance() {
    	Account theAccount = getAccount(1,2,3.0);
    	dao.save(theAccount);
    	Double increasedBalance = dao.increaseBalance(theAccount.getId(),1.0);
    	assertEquals(theAccount.getBalance(),increasedBalance);
    	assertNotNull(increasedBalance);
    }
    
    
    private Account getAccount(int account_id, int user_id, double balance) {
    	Account theAccount = new Account();
    	theAccount.setId(account_id);
    	theAccount.setUserId(user_id);
    	theAccount.setBalance(balance);
    	return theAccount;
    }
    
    
    
    
    
}
