package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.categorie;
import model.Bean.categorieFK;

public class categorieDAO {
	public List<categorie> getAllCategorie() {
		List<categorie> listCategorie = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT * FROM categories";
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next()) {
				listCategorie.add(new categorie(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5)));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listCategorie;
	}
	
	public void addCategorie(String name, String slug, String status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "INSERT INTO categories (name, slug, status) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, name);
			    preparedStatement.setString(2, slug);
			    preparedStatement.setString(3, status);
			    preparedStatement.executeUpdate();
			}

		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void deleteCategorie(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "DELETE FROM categories WHERE id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setInt(1, id);			    
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public categorie getCategorie(int id) {
		categorie Categorie = new categorie(id, null, null, null, null);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT * FROM categories WHERE id = "+id;
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				System.out.println(rs.getInt(1));
				Categorie.setId(rs.getInt(1));
				Categorie.setName(rs.getString(2));
				Categorie.setCreated_at(rs.getDate(3));
				Categorie.setUpdated_at(rs.getDate(4));
				Categorie.setStatus(rs.getString(5));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Categorie;
	}
	
	public void updateCategorie(int id, String name, Date created_at, Date updated_at, String status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "UPDATE categories SET name=?, created_at=?, updated_at=?, status=? WHERE id=?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, name);
			    preparedStatement.setDate(2, created_at);
			    preparedStatement.setDate(3, updated_at);
			    preparedStatement.setString(4, status);
			    preparedStatement.setInt(5, id);
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public List<categorieFK> getAllCategorieFK() {
		List<categorieFK> listCategorieFK = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT id,name FROM categories";
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next()) {
				listCategorieFK.add(new categorieFK(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listCategorieFK;
	}
}
