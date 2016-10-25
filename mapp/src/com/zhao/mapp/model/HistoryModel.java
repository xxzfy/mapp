package com.zhao.mapp.model;

public class HistoryModel {
	private Long id;
	private String index;
	private String person;
	private String opinion;
	private String result;
	private String sign;
	private String signpath;
	private String signpwd;
	private String signdate;
	public HistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HistoryModel(Long id,String index, String person, String opinion, String result, String sign, String signpath, String signpwd,String signdate) {
		super();
		this.id=id;
		this.index = index;
		this.person = person;
		this.opinion = opinion;
		this.result = result;
		this.sign = sign;
		this.signpath = signpath;
		this.signpwd = signpwd;
		this.signdate=signdate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignpath() {
		return signpath;
	}
	public void setSignpath(String signpath) {
		this.signpath = signpath;
	}
	public String getSignpwd() {
		return signpwd;
	}
	public void setSignpwd(String signpwd) {
		this.signpwd = signpwd;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	
}
