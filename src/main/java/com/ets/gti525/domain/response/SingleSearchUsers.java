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
	private String debitCardNumber, creditCardNumber, cardholderName;
	
	public SingleSearchUsers (String cardholderName, String debitCardNumber, String creditCardNumber) {
		this.cardholderName = cardholderName;
		this.debitCardNumber = debitCardNumber;
		this.creditCardNumber = creditCardNumber;
	}
	
	public SingleSearchUsers (String cardholderName) {
		this.cardholderName = cardholderName;
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

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}


	
	
}
