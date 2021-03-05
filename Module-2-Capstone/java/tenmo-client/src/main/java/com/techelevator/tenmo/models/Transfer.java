package com.techelevator.tenmo.models;

public class Transfer {
	
	private Long id;
	private Integer typeId;
	//private String typeDescription;
	private Integer statusId;
	//private String statusDescription;
	private Integer accountFrom;
	private Integer accountTo;
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
	public Integer getTypeId() {
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
	public Integer getStatusId() {
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
	public Integer getAccountFrom() {
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
	public Integer getAccountTo() {
		return accountTo;
	}
	/**
	 * @param accountTo the accountTo to set
	 */
	public void setAccountTo(Integer accountTo) {
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
