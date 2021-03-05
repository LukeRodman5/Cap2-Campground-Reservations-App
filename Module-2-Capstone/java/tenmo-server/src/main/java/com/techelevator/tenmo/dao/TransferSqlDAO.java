package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;

@Component
public class TransferSqlDAO implements TransferDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Transfer createTransfer(Transfer newTransfer) {
				
		String insertTransfer = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		newTransfer.setId(getNextTransferId()); // write method for getting the next transferId;
		
		
		//newTransfer.setTypeId();
		
		//newTransfer.setStatusId(1);
		
		// In the App code, we need the user to pass in AccountFrom, AccountTo and Amount when they create a Transfer
		
		jdbcTemplate.update(insertTransfer, newTransfer.getId(),
											newTransfer.getTypeId(),
											newTransfer.getStatusId(),
											newTransfer.getAccountFrom(),
											newTransfer.getAccountTo(),
											newTransfer.getAmount());
		
		Transfer theTransfer = null;
		String selectTransfer = "SELECT * " + 
				" FROM transfers " + 
				" WHERE transfer_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectTransfer, newTransfer.getId());
		if (results.next()) {
			theTransfer = mapRowToTransfer(results);
		}
		return theTransfer;
	}
	
	public List<Transfer> findTransfersByUserId(Integer userId) {
		
		ArrayList<Transfer> transfers = new ArrayList<>();
		
		String sqlFindTransfersByUserId = "SELECT transfers.transfer_id, transfers.transfer_type_id, transfers.transfer_status_id, transfers.account_from, transfers.account_to, transfers.amount" + 
				"        FROM accounts" + 
				"        JOIN transfers" + 
				"        ON transfers.account_from = accounts.account_id" + 
				"        WHERE accounts.user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindTransfersByUserId, userId);
		
		while (results.next()) {
			Transfer theTransfer = mapRowToTransfer(results);
			transfers.add(theTransfer);
		}
		
		return transfers;
		
	}
	
	public Transfer findTransferById(long transferID) {
		
		Transfer transfer = new Transfer();
		String sqlFindTransfersByTransferId = 
				"		 SELECT *" + 
				"        FROM transfers" + 
				"        WHERE transfer_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindTransfersByTransferId, transferID);
		
		while (results.next()) {
			Transfer theTransfer = mapRowToTransfer(results);
			transfer = theTransfer;
		}
		return transfer;
	}
	
	
	
	private long getNextTransferId() {
		
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_transfer_id')");
		
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new transfer");
		}
		
	}
	
	private Transfer mapRowToTransfer(SqlRowSet results) {
		
		Transfer theTransfer;
		
		theTransfer = new Transfer();
		
		theTransfer.setId(results.getLong("transfer_id"));
		theTransfer.setTypeId(results.getInt("transfer_type_id"));
		theTransfer.setStatusId(results.getInt("transfer_status_id"));
		theTransfer.setAccountFrom(results.getInt("account_from"));
		theTransfer.setAccountTo(results.getInt("account_to"));
		theTransfer.setAmount(results.getDouble("amount"));
		
		return theTransfer;
		
	}
	

}
