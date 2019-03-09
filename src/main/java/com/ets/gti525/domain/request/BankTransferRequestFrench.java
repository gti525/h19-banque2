package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;


public class BankTransferRequestFrench {
	
	private long numeroDeCompteSource;
	
	@NotNull
	private long numeroDeCompteDestination;
	
	@NotNull
	private double montant;
	
	public BankTransferRequestFrench(BankTransferRequest request) {
		this.numeroDeCompteSource = request.getSourceAccountNumber();
		this.numeroDeCompteDestination = request.getTargetAccountNumber();
		this.montant = request.getAmount();
	}

	public long getNumeroDeCompteSource() {
		return numeroDeCompteSource;
	}

	public void setNumeroDeCompteSource(long numeroDeCompteSource) {
		this.numeroDeCompteSource = numeroDeCompteSource;
	}

	public long getNumeroDeCompteDestination() {
		return numeroDeCompteDestination;
	}

	public void setNumeroDeCompteDestination(long numeroDeCompteDestination) {
		this.numeroDeCompteDestination = numeroDeCompteDestination;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}



}
