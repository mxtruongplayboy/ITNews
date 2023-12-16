package model.Bean;

import java.sql.Date;

public class PostDetailVM {
	private int id;
	private String title;
	private String content;
	private Date creationDate;
	public PostDetailVM(int id, String title, String content, Date creationDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
