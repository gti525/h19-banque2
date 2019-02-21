package com.ets.gti525.domain.response;

/**
 * Description : Class representing a single result of a users research.
 * (Used by the class « SingleSearchUsers » for example.)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 21-01-2019
 */
public class SingleSearchUsers {
	private String firstName, lastname, debitCardNumber, creditCardNumber;
	
	public SingleSearchUsers (String firstName, String lastName, String debitCardNumber, String creditCardNumber) {
		this.firstName = firstName;
		this.lastname = lastName;
		this.debitCardNumber = debitCardNumber;
		this.creditCardNumber = creditCardNumber;
	}
	
	public SingleSearchUsers (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastname = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getDebitCardNumber() {
		return debitCardNumber;
	}

	public void setDebitCardNumber(String debitCardNumber) {
		this.debitCardNumber = debitCardNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
}
