package com.claims.models;

import java.time.LocalDate;

public class Claims {
	private int id;
	private String memberid;
	private LocalDate req_date;
	private LocalDate process_date;
	private String reason;
	private int final_amount;
	private String status;
	private String rej_reason;
	
	public String getRej_reason() {
		return rej_reason;
	}
	public void setRej_reason(String rej_reason) {
		this.rej_reason = rej_reason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
	public LocalDate getProcess_date() {
		return process_date;
	}
	public void setProcess_date(LocalDate process_date) {
		this.process_date = process_date;
	}
	public LocalDate getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = LocalDate.parse(req_date);
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getFinal_amount() {
		return final_amount;
	}
	public void setFinal_amount(int final_amount) {
		this.final_amount = final_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Claims [id=" + id + ", member_id=" + memberid + ", req_date=" + req_date + ", reason=" + reason
				+ ", final_amount=" + final_amount + ", status=" + status + "]";
	}
	
	

}
