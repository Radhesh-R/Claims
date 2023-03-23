package com.claims.models;

public class ClaimSearchDTO {
	private String mname;
	private String memberid;
	private String from;
	private String to;
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "ClaimSearchDTO [mname=" + mname + ", memberid=" + memberid + ", from=" + from + ", to=" + to + "]";
	}
	
	
}
