package cn.rr.entity;

public class Food {
	private Integer fid;
	private String fname;
	private Cuisine cid;
	private double price;
	private double vipprice;
	private String intro;
	private String photourl;
	public Food() {}

	public Food(Integer fid, String fname, Cuisine cid, double price, double vipprice, String intro, String photourl) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.cid = cid;
		this.price = price;
		this.vipprice = vipprice;
		this.intro = intro;
		this.photourl = photourl;
	}


	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Cuisine getCid() {
		return cid;
	}
	public void setCid(Cuisine cid) {
		this.cid = cid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getVipprice() {
		return vipprice;
	}
	public void setVipprice(double vipprice) {
		this.vipprice = vipprice;
	}
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	
	
}
