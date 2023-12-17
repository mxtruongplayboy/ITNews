package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.PostDescRequest;
import model.Bean.PostDescVM;
import model.Bean.PostDetailVM;
import model.Bean.accountFK;
import model.Bean.categorieFK;
import model.Bean.post;


public class postDAO {
	public List<post> getAllPost() {
		List<post> listPost = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT posts.*, categories.name, accounts.fullname\r\n"
					+ "FROM posts\r\n"
					+ "JOIN categories ON posts.category_id = categories.id\r\n"
					+ "JOIN accounts ON posts.accounts_id = accounts.id;\r\n"
					+ "";
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next()) {
				listPost.add(new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(12)), new accountFK(rs.getInt(9), rs.getString(13)), rs.getBoolean(10), rs.getString(11)));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listPost;
	}
	
	public List<post> getAllPostByAccountID(int id) {
		List<post> listPost = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT posts.*, categories.name, accounts.fullname\r\n"
					+ "FROM posts\r\n"
					+ "JOIN categories ON posts.category_id = categories.id\r\n"
					+ "JOIN accounts ON posts.accounts_id = accounts.id\r\n"
					+ "WHERE accounts.id = "+ id
					+ "";
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next()) {
				listPost.add(new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(12)), new accountFK(rs.getInt(9), rs.getString(13)), rs.getBoolean(10), rs.getString(11)));
			}
			System.out.println(listPost.size());
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listPost;
	}
	
	public post getFirstPostByAccountID(int id) {
		post Post = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT posts.*, categories.name, accounts.fullname " +
	                 "FROM posts " +
	                 "JOIN categories ON posts.category_id = categories.id " +
	                 "JOIN accounts ON posts.accounts_id = accounts.id " +
	                 "WHERE accounts.id = "+ id +
	                 " LIMIT 1;";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(12)), new accountFK(rs.getInt(9), rs.getString(13)), rs.getBoolean(10), rs.getString(11));
				System.out.println(rs.getInt(1));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Post;
	}
	
	public post getFirstPostByCategorieID(int id) {
		post Post = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT posts.*, categories.name, accounts.fullname " +
	                 "FROM posts " +
	                 "JOIN categories ON posts.category_id = categories.id " +
	                 "JOIN accounts ON posts.accounts_id = accounts.id " +
	                 "WHERE categories.id = "+ id +
	                 " LIMIT 1;";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(12)), new accountFK(rs.getInt(9), rs.getString(13)), rs.getBoolean(10), rs.getString(11));
				System.out.println(rs.getInt(1));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Post;
	}
	
	public void addPost(String title, String image, String content, String status, int categoryID, int accountsID, boolean hot, String description) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			String sql = "INSERT INTO posts (title, image, content, status, category_id, accounts_id, hot, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, title);
			    preparedStatement.setString(2, image);
			    preparedStatement.setString(3, content);
			    preparedStatement.setString(4, status);
			    preparedStatement.setInt(5, categoryID);
			    preparedStatement.setInt(6, accountsID);
			    preparedStatement.setBoolean(7, hot);
			    preparedStatement.setString(8, description);
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	public List<PostDescVM> getAllPostDescVM(PostDescRequest request) {
		List<PostDescVM> listPost = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			String sql = request.getCategoryId() == 0 ? 
					"SELECT posts.id, posts.title, posts.image, " +
		             "posts.created_at, posts.hot as isHot , posts.description as description , categories.id AS category_id, categories.name " +
		             "FROM posts " +
		             "JOIN categories ON posts.category_id = categories.id " +
		             "WHERE (posts.title LIKE ? OR posts.content LIKE ?) and posts.status LIKE 'Hoạt động' " +
		             "ORDER BY posts.hot DESC, posts.created_at DESC " +
		             "LIMIT 10" : 
					"SELECT posts.id, posts.title, posts.image, " +
		             "posts.created_at, posts.hot as isHot, posts.description as description , categories.id AS category_id, categories.name " +
		             "FROM posts " +
		             "JOIN categories ON posts.category_id = categories.id " +
		             "WHERE categories.id = ? AND (posts.title LIKE ? OR posts.content LIKE ?) and posts.status LIKE 'Hoạt động' " +
		             "ORDER BY posts.hot DESC, posts.created_at DESC " +
		             "LIMIT 10";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			if (request.getCategoryId() == 0) {
				preparedStatement.setString(1, "%" + request.getKeyword() + "%");
				preparedStatement.setString(2, "%" + request.getKeyword() + "%");
			} else {
				preparedStatement.setInt(1, request.getCategoryId());
				preparedStatement.setString(2, "%" + request.getKeyword() + "%");
				preparedStatement.setString(3, "%" + request.getKeyword() + "%");
			}
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				listPost.add(new PostDescVM(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString("description") , rs.getDate(4), rs.getBoolean("isHot")));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listPost;
	}

	public boolean deletePost(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			String sql = "DELETE FROM posts WHERE id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setInt(1, id);			    
			    if (preparedStatement.executeUpdate() == 0) {
			    	return false;
			    }
			    return true;
			}
		}
		catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	public PostDetailVM getPostDetailById(int id) {
		PostDetailVM postDetail = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			String sql = "SELECT posts.id as id, posts.title as title, posts.content as content, " +
		             "posts.created_at as creationDate, categories.id AS category_id, categories.name " +
		             "FROM posts " +
		             "JOIN categories ON posts.category_id = categories.id " +
		             "WHERE posts.id = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				postDetail = new PostDetailVM(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getDate("creationDate"));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return postDetail;
	}
	
	public post getPostByID(int id) {
		post Post = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT posts.*, categories.name, accounts.fullname " +
	                 "FROM posts " +
	                 "JOIN categories ON posts.category_id = categories.id " +
	                 "JOIN accounts ON posts.accounts_id = accounts.id " +
	                 "WHERE posts.id = "+ id +
	                 ";";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(12)), new accountFK(rs.getInt(9), rs.getString(13)), rs.getBoolean(10), rs.getString(11));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Post;
	}

	public void updatePost(int id, String title, String content, String status, int categoryID,
			int accountID, boolean hot, String description, Date updated_at) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt", "root", "");
	        
	        // Sử dụng PreparedStatement để tránh SQL Injection
	        String sql = "UPDATE posts SET title=?, content=?, status=?, category_id=?, accounts_id=?, hot=?, description=?, updated_at=? WHERE id=?";
	        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	            preparedStatement.setString(1, title);
	            preparedStatement.setString(2, content);
	            preparedStatement.setString(3, status);
	            preparedStatement.setInt(4, categoryID);
	            preparedStatement.setInt(5, accountID);
	            preparedStatement.setBoolean(6, hot);
	            preparedStatement.setString(7, description);
	            preparedStatement.setDate(8, updated_at);
	            
	            // Thiết lập tham số WHERE (id) cho truy vấn UPDATE
	            preparedStatement.setInt(9, id);
	            
	            preparedStatement.executeUpdate();
	        }
	    } catch (Exception e) {
	        System.out.print(e);
	    }
	}

	public boolean updateImagePost(int id, String fileName) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt", "root", "");
	        
	        // Sử dụng PreparedStatement để tránh SQL Injection
	        String sql = "UPDATE posts SET image=? WHERE id=?";
	        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	            preparedStatement.setString(1, fileName);
	            
	            // Thiết lập tham số WHERE (id) cho truy vấn UPDATE
	            preparedStatement.setInt(2, id);
	            
	            if (preparedStatement.executeUpdate() == 0)
	            	return false;
	            return true;
	        }
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return false;
	}
}
