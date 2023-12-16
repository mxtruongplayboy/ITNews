package model.Bean;

import java.sql.Date;

public class post {
	private int id;
	private String title;
	private String image;
	private String content;
	private Date created_at;
	private Date updated_at;
	private String status;
	private categorieFK categorieFK;
	private accountFK accountFK;
	private boolean hot;
	private String description;
	public post(int id, String title, String image, String content, Date created_at, Date updated_at,
			String status, model.Bean.categorieFK categorieFK, model.Bean.accountFK accountFK, boolean hot, String description) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.status = status;
		this.categorieFK = categorieFK;
		this.accountFK = accountFK;
		this.hot = hot;	
		this.description = description;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public categorieFK getCategorieFK() {
		return categorieFK;
	}
	public void setCategorieFK(categorieFK categorieFK) {
		this.categorieFK = categorieFK;
	}
	public accountFK getAccountFK() {
		return accountFK;
	}
	public void setAccountFK(accountFK accountFK) {
		this.accountFK = accountFK;
	}
	public boolean getHot() {
		return hot;
	}
	public void setHot(boolean hot) {
		this.hot = hot;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
