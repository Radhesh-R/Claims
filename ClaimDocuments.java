package com.claims.models;

public class ClaimDocuments {

	private int id;
	private int claim_id;
	private String doc1;
	private String doc2;
	private String doc3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}
	public String getDoc1() {
		return doc1;
	}
	public void setDoc1(String doc1) {
		this.doc1 = doc1;
	}
	public String getDoc2() {
		return doc2;
	}
	public void setDoc2(String doc2) {
		this.doc2 = doc2;
	}
	public String getDoc3() {
		return doc3;
	}
	public void setDoc3(String doc3) {
		this.doc3 = doc3;
	}
	@Override
	public String toString() {
		return "ClaimDocuments [id=" + id + ", claim_id=" + claim_id + ", doc1=" + doc1 + ", doc2=" + doc2 + ", doc3="
				+ doc3 + "]";
	}
	
}
