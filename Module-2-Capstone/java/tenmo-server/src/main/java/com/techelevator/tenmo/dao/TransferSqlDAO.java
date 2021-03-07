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
	
	@Override
	public void save(Transfer newTransfer) {
				
		String insertTransfer = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		newTransfer.setId(getNextTransferId()); // write method for getting the next transferId;
		
		// All created Transfers will be of type "Send"
		newTransfer.setTypeId(2);
		
		// All created Transfers will be of type "Approved" initially
		newTransfer.setStatusId(2);
		
		jdbcTemplate.update(insertTransfer, newTransfer.getId(),
											newTransfer.getTypeId(),
											newTransfer.getStatusId(),
											newTransfer.getAccountFrom(),
											newTransfer.getAccountTo(),
											newTransfer.getAmount());
	}
	
	@Override
	public Transfer findTransferById(long transferID) {
		
		Transfer theTransfer = null;
		
		String sqlFindTransferById = 
				"SELECT * " + 
				" FROM transfers " + 
				" WHERE transfer_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindTransferById, transferID);
		
		if(results.next()) {
			theTransfer = mapRowToTransfer(results);
		}
		return theTransfer;
	}
	
	@Override
	public List<Transfer> findTransfersByAccountId(int accountId) {
		
		ArrayList<Transfer> transfers = new ArrayList<>();
		
		String sqlFindTransfersByUserId = "SELECT * " +
				                          " FROM transfers " +
				                          " WHERE account_from = ? OR account_to = ?";
		
		SqlRowSet transfersFromTable = jdbcTemplate.queryForRowSet(sqlFindTransfersByUserId, accountId);
		
		while (transfersFromTable.next()) {
			Transfer theTransfer = mapRowToTransfer(transfersFromTable);
			transfers.add(theTransfer);
		}
		
		return transfers;
		
	}
	
	@Override
	public void update(Transfer transfer) {
		String sqlUpdate = "UPDATE transfers SET transfer_type_id = ?, transfer_status_id = ?, account_from = ?, account_to = ?, amount = ? WHERE transfer_id = ?";
		jdbcTemplate.update(sqlUpdate, transfer.getTypeId(), transfer.getStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount(), transfer.getId());
	}
	
	@Override
	public void delete(long id) {
		String deleteATransfer = "DELETE from transfers WHERE transfer_id = ?";
		jdbcTemplate.update(deleteATransfer, id);
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
