package com.techelevator.tenmo.model;

public class Transfer {
	
	private Long id;
	private int typeId;
	private int statusId;
	private int accountFrom;
	private int accountTo;
	private Double amount;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the accountFrom
	 */
	public int getAccountFrom() {
		return accountFrom;
	}
	/**
	 * @param accountFrom the accountFrom to set
	 */
	public void setAccountFrom(Integer accountFrom) {
		this.accountFrom = accountFrom;
	}
	/**
	 * @return the accountTo
	 */
	public int getAccountTo() {
		return accountTo;
	}
	/**
	 * @param accountTo the accountTo to set
	 */
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
