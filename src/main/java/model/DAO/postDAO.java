package model.DAO;

import java.sql.Connection;
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
				listPost.add(new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(11)), new accountFK(rs.getInt(9), rs.getString(12)), rs.getBoolean(10)));
			}
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
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(11)), new accountFK(rs.getInt(9), rs.getString(12)), rs.getBoolean(10));
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
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), new categorieFK(rs.getInt(8), rs.getString(11)), new accountFK(rs.getInt(9), rs.getString(12)), rs.getBoolean(10));
				System.out.println(rs.getInt(1));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Post;
	}
	
	public void addPost(String title, String image, String content, String status, int categoryID, int accountsID, boolean hot) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "INSERT INTO posts (title, image, content, status, category_id, accounts_id, hot) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, title);
			    preparedStatement.setString(2, image);
			    preparedStatement.setString(3, content);
			    preparedStatement.setString(4, status);
			    preparedStatement.setInt(5, categoryID);
			    preparedStatement.setInt(6, accountsID);
			    preparedStatement.setBoolean(7, hot);
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
		             "posts.created_at, posts.hot as isHot , categories.id AS category_id, categories.name " +
		             "FROM posts " +
		             "JOIN categories ON posts.category_id = categories.id " +
		             "WHERE (posts.title LIKE ? OR posts.content LIKE ?) " +
		             "ORDER BY posts.hot DESC, posts.created_at DESC " +
		             "LIMIT 10" : 
					"SELECT posts.id, posts.title, posts.image, " +
		             "posts.created_at, posts.hot as isHot, categories.id AS category_id, categories.name " +
		             "FROM posts " +
		             "JOIN categories ON posts.category_id = categories.id " +
		             "WHERE categories.id = ? AND (posts.title LIKE ? OR posts.content LIKE ?) " +
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
				listPost.add(new PostDescVM(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean("isHot")));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listPost;
	}

	public void deletePost(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "DELETE FROM posts WHERE id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setInt(1, id);			    
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
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
}
