package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name = "DemoTest")
public class DemoTestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "aadhar")
	private Long aadharCard;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "amount")
	private Double depositAmount;

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
	 * @return the aadharCard
	 */
	public Long getAadharCard() {
		return aadharCard;
	}

	/**
	 * @param aadharCard the aadharCard to set
	 */
	public void setAadharCard(Long aadharCard) {
		this.aadharCard = aadharCard;
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

	@Override
	public String toString() {
		return "DemoTest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", depositAmount="
				+ depositAmount + "]";
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
	
	
	
}
