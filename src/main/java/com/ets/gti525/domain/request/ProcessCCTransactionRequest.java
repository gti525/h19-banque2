package com.ets.gti525.domain.request;

public class ProcessCCTransactionRequest {
	
	private Integer transactionID;
	private String action;
	
	public static final String ACTION_COMMIT =  "COMMIT";
	public static final String ACTION_CANCEL = "CANCEL";
	
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public boolean validAction() {
		if(action.equals(ACTION_CANCEL))
			return true;
		if(action.equals(ACTION_COMMIT))
			return true;
		
		return false;
	}
	

}
