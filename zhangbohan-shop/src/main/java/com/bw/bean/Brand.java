package com.bw.bean;

public class Brand {
	private Integer bid;
	private String bname;
	private Integer did;
	private String dname;
	private String dtime;
	private Integer dbrand;
	private Integer id;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public Integer getDbrand() {
		return dbrand;
	}
	public void setDbrand(Integer dbrand) {
		this.dbrand = dbrand;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Brand(Integer bid, String bname, Integer did, String dname, String dtime, Integer dbrand, Integer id) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.did = did;
		this.dname = dname;
		this.dtime = dtime;
		this.dbrand = dbrand;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Brand [bid=" + bid + ", bname=" + bname + ", did=" + did + ", dname=" + dname + ", dtime=" + dtime
				+ ", dbrand=" + dbrand + ", id=" + id + "]";
	}
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
