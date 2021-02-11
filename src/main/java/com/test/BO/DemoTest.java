package com.test.BO;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class DemoTest {
	@ApiModelProperty(required = true,dataType = "String")
	private String firstName;
	@ApiModelProperty(required = true,dataType = "String")
	private String lastName;
	@ApiModelProperty(required = true,dataType = "Long")
	@Min(value = 12, message = "Aadhar card length  should be of minimum 12 digit")
	private long aadharCard;
	@ApiModelProperty(required = true,dataType = "Double")
	@Min(value = 0L, message = "deposit amount must be positive")
	private Double depositAmount;

	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	/**
	 * @return the depositAmount
	 */
	public Double getDepositAmount() {
		return depositAmount;
	}

	/**
	 * @param depositAmount the depositAmount to set
	 */
	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * @return the aadharCard
	 */
	public long getAadharCard() {
		return aadharCard;
	}

	/**
	 * @param aadharCard the aadharCard to set
	 */
	public void setAadharCard(long aadharCard) {
		this.aadharCard = aadharCard;
	}

	@Override
	public String toString() {
		return "DemoTest [firstName=" + firstName + ", lastName=" + lastName + ", aadharCard="
				+ aadharCard + ", depositAmount=" + depositAmount + "]";
	}



}
