package com.claims.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Member {

	private String memberid;
	private String fname;
	private String lname;	
	private LocalDate dob;
	private String address;
	private String phone;
	private String email;
	private String gender;
	private int nominee_count; //1-3
	private String insurance_type;
	private int insurance_amount;
	private int max_claim_amount;
	private String citizen_type;
	private LocalDateTime created_on;
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = LocalDate.parse(dob);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNominee_count() {
		return nominee_count;
	}
	public void setNominee_count(int nominee_count) {
		this.nominee_count = nominee_count;
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
	public int getMax_claim_amount() {
		return max_claim_amount;
	}
	public void setMax_claim_amount(int max_claim_amount) {
		this.max_claim_amount = max_claim_amount;
	}
	public String getCitizen_type() {
		return citizen_type;
	}
	public void setCitizen_type(String citizen_type) {
		this.citizen_type = citizen_type;
	}
	public LocalDateTime getCreated_on() {
		return created_on;
	}
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}
	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", nominee_count="
				+ nominee_count + ", insurance_type=" + insurance_type + ", insurance_amount=" + insurance_amount
				+ ", max_claim_amount=" + max_claim_amount + ", citizen_type=" + citizen_type + ", created_on="
				+ created_on + "]";
	}
	
	
}
