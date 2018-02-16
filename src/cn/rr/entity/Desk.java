package cn.rr.entity;

import java.util.Date;

public class Desk {
	private Integer did;//±àºÅ
	private String dname;//×ÀÃû
	private Integer status;//Ô¤¶¨×´Ì¬    	0£º¿ÕÏĞ	1£ºÔ¤¶¨
	private Date presettime;//Ô¤¶¨Ê±¼ä
	public Desk(){}
	
	public Desk(Integer did, String dname, Integer status, Date presettime) {
		super();
		this.did = did;
		this.dname = dname;
		this.status = status;
		this.presettime = presettime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPresettime() {
		return presettime;
	}
	public void setPresettime(Date presettime) {
		this.presettime = presettime;
	}
	
	
}
