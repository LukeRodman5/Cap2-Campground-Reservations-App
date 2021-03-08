package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.model.Account;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDAOTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction 
	 * 
	 * private static final String TEST_COUNTRY = "XYZ";
	 *
	 * see below associated noted out code
	 */
	
	private static SingleConnectionDataSource dataSource;
    private AccountSqlDAO dao; 
    
    @BeforeClass
    public static void setupDataSource() {
    	dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("postgresql://localhost:8080/tenmo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(false);
    }
    /*
     * @Before
	 * public void setup() {
	 * String sqlInsertCountry = "INSERT INTO country (code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2) VALUES (?, 'Afghanistan', 'Asia', 'Southern and Central Asia', 652090, 1919, 22720000, 45.9000015, 5976.00, NULL, 'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 1, 'AF')";
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
	 * dao = new JDBCCityDAO(dataSource);
	 * }
	 */        
    @AfterClass
    public static void closeDataSource() throws SQLException {
    	dataSource.destroy();
    }
    /*
     * @Before
     * public void setup() {
     * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
	 * dao = new JDBCCityDAO(dataSource);
     *}
     *
	 * After each test, we roll-back any changes that were made to the database so that
	 * everything is clean for the next test 
	 */
    
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
    @Test
    public void returnsBalanceByAccountId() {
    	Account theAccount = getAccount((long) 1, 2, 3.0);
    	dao.save(theAccount);
    	Double savedBalance = dao.findBalanceByAccountId(theAccount.getId());
    	assertEquals(theAccount.getBalance(),savedBalance);
    	assertNotNull(savedBalance);
    }
    @Test
    public void returnsIncreaseBalance() {
    	Account theAccount = getAccount(1,2,3.0);
    	dao.save(theAccount);
    	//Double increasedBalance = dao.increaseBalance(theAccount.getId(),1.0);
    	//assertEquals(theAccount.getBalance(),increasedBalance);
    	//assertNotNull(increasedBalance);
    }
    @Test
    public void returnsDecreaseBalance() {
    	Account theAccount = getAccount(1,2,3.0);
    	dao.save(theAccount);
    	//Double decreasedBalance = dao.decreaseBalance(theAccount.getId(),1.0);
    	//assertEquals(theAccount.getBalance(),decreasedBalance);
    	//assertNotNull(decreasedBalance);
    }    
    
    private Account getAccount(long account_id, int user_id, double balance) {
    	Account theAccount = new Account();
    	theAccount.setId(account_id);
    	theAccount.setUserId(user_id);
    	theAccount.setBalance(balance);
    	return theAccount;
    } 
    
}