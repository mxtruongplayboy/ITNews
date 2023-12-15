package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				listPost.add(new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8), new categorieFK(rs.getInt(9), rs.getString(12)), new accountFK(rs.getInt(10), rs.getString(13)), rs.getBoolean(11)));
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
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8), new categorieFK(rs.getInt(9), rs.getString(12)), new accountFK(rs.getInt(10), rs.getString(13)), rs.getBoolean(11));
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
				Post = new post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8), new categorieFK(rs.getInt(9), rs.getString(12)), new accountFK(rs.getInt(10), rs.getString(13)), rs.getBoolean(11));
				System.out.println(rs.getInt(1));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Post;
	}
}
