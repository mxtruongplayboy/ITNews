package model.Bean;

public class PostDescRequest {
	private String keyword;
	private int categoryId;
	private int size;
	public PostDescRequest(String keyword, int categoryId, int size) {
		super();
		this.keyword = keyword;
		this.categoryId = categoryId;
		this.size = size;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
