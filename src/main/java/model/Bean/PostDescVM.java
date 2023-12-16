package model.Bean;

import java.sql.Date;

public class PostDescVM {
	private int id;
	private String title;
	private String imgUrl;
	private Date creationDate;
	private boolean isHot;
	private boolean isLatest;
	public PostDescVM(int id, String title, String imgUrl, Date creationDate, boolean isHot) {
		super();
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl;
		this.creationDate = creationDate;
		this.isHot = isHot;
		this.isLatest = false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isHot() {
		return isHot;
	}
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
	public boolean isLatest() {
		return isLatest;
	}
	public void setLatest(boolean isLatest) {
		this.isLatest = isLatest;
	}
}
