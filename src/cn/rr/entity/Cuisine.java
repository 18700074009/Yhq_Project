package cn.rr.entity;

public class Cuisine {
	private Integer cid;
	private String cname;
	
	public Cuisine(){};
	
	public Cuisine(Integer cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
