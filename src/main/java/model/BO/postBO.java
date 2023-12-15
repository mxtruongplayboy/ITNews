package model.BO;

import java.util.List;

import model.Bean.post;
import model.DAO.postDAO;

public class postBO {
	public List<post> getAllpost() {
		postDAO postDAO = new postDAO();
		return postDAO.getAllPost();
	}
}
