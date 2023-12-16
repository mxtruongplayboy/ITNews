package model.BO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import model.Bean.PostDescRequest;
import model.Bean.PostDescVM;
import model.Bean.PostDetailVM;
import model.Bean.post;
import model.DAO.postDAO;

public class postBO {
	public List<post> getAllpost() {
		postDAO postDAO = new postDAO();
		return postDAO.getAllPost();
	}
	
	public List<post> getAllpostByAccountID(int id) {
		postDAO postDAO = new postDAO();
		return postDAO.getAllPostByAccountID(id);
	}
	
	public void addPost(String title, String image, String content, String status, int categoryID, int accountsID, boolean hot, String description) {
		postDAO postDAO = new postDAO();
		postDAO.addPost(title, image, content, status, categoryID, accountsID, hot, description);
	}
	
	public void updatePost(int id, String title, String image, String content, String status, int categoryID, int accountID,
			boolean hot, String description) {
		postDAO postDAO = new postDAO();
		postDAO.updatePost(id, title, image, content, status, categoryID, accountID, hot, description);
	}
	
	public void deletePost(int id) {
		postDAO postDAO = new postDAO();
		postDAO.deletePost(id);
	}
	
	public List<PostDescVM> getAllPostDescVM(PostDescRequest request){
		postDAO postDAO = new postDAO();
		List<PostDescVM> list = postDAO.getAllPostDescVM(request);
		
		LocalDate currentDate = LocalDate.now();

        for (PostDescVM item : list) {
            // Chuyển đổi java.sql.Date thành java.util.Date
            java.util.Date utilDate = new java.util.Date(item.getCreationDate().getTime());
            
            // Chuyển đổi java.util.Date thành LocalDate
            LocalDate creationLocalDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Kiểm tra nếu bài viết được tạo trong khoảng 3 ngày gần đây
            if (currentDate.minusDays(3).isBefore(creationLocalDate) || currentDate.isEqual(creationLocalDate)) {
                item.setLatest(true);
            }
        }
		
		return list;
	}
	
	public PostDetailVM getPostDetailById(int id) {
		postDAO postDAO = new postDAO();
		return postDAO.getPostDetailById(id);
	}
	
	public post getPostByID(int id) {
		postDAO postDAO = new postDAO();
		return postDAO.getPostByID(id);
	}
}
