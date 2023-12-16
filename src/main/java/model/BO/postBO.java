package model.BO;

import java.util.List;

import model.Bean.post;
import model.DAO.postDAO;

public class postBO {
	public List<post> getAllpost() {
		postDAO postDAO = new postDAO();
		return postDAO.getAllPost();
	}
	
	public void addPost(String title, String image, String content, String status, int categoryID, int accountsID, boolean hot) {
		postDAO postDAO = new postDAO();
		postDAO.addPost(title, image, content, status, categoryID, accountsID, hot);
	}
	
	public void deletePost(int id) {
		postDAO postDAO = new postDAO();
		postDAO.deletePost(id);
	}
}
