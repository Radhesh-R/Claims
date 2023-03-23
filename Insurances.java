package com.claims.models;

public class Insurances {
	private int id;
	private String insurance_type;
	private int insurance_amount;
	private int percentage;
	private int max_claim_amount;
	
	public int getMax_claim_amount() {
		return max_claim_amount;
	}
	public void setMax_claim_amount(int max_claim_amount) {
		this.max_claim_amount = max_claim_amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInsurance_type() {
		return insurance_type;
	}
	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}
	public int getInsurance_amount() {
		return insurance_amount;
	}
	public void setInsurance_amount(int insurance_amount) {
		this.insurance_amount = insurance_amount;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "Insurances [insurance_type=" + insurance_type + ", insurance_amount=" + insurance_amount + ", percentage="
				+ percentage + "]";
	}


}
