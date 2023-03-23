package com.claims.models;

public class Admin {
	private String userid;
	private String uname;
	private String pwd;
	
	
	public Admin(String userid, String pwd) {
		this.userid = userid;
		this.pwd = pwd;
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Admin [userid=" + userid + ", uname=" + uname + ", pwd=" + pwd + "]";
	}
	
	
}
