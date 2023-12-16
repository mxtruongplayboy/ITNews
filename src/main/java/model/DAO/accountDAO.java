package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.account;
import model.Bean.accountFK;
import model.Bean.categorie;
import model.Bean.post;


public class accountDAO {
	public List<account> getAllaccount() {
		List<account> listAccount = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT * FROM accounts";
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next()) {
				listAccount.add(new account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8)));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return listAccount;
	}
	
	public void addAccount(String username, String password, String fullname, String role, String status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "INSERT INTO accounts (username, password, fullname, role, status) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, username);
			    preparedStatement.setString(2, password);
			    preparedStatement.setString(3, fullname);
			    preparedStatement.setString(4, role);
			    preparedStatement.setString(5, status);
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void deleteAccount(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "DELETE FROM accounts WHERE id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setInt(1, id);			    
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void updateAccount(int id, String username, String fullname, String role, Date created_at, Date updated_at, String status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "UPDATE accounts SET username=?, fullname=?, role=?, created_at=?, updated_at=?, status=? WHERE id=?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, username);
			    preparedStatement.setString(2, fullname);
			    preparedStatement.setString(3, role);
			    preparedStatement.setDate(4, created_at);
			    preparedStatement.setDate(5, updated_at);
			    preparedStatement.setString(6, status);
			    preparedStatement.setInt(7, id);
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public account getAccount(int id) {
		account Account = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT * FROM accounts WHERE id = "+id;
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				Account = new account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Account;
	}
	
	public void updatePassword(int id, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "UPDATE accounts SET password=? WHERE id=?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			    preparedStatement.setString(1, password);
			    preparedStatement.setInt(2, id);	
			    preparedStatement.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public boolean checkUsername(String username) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			String sql = "SELECT * FROM accounts WHERE username = '"+username+"'";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				return false;
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return true;
	}
	
	public account getAccountByUsPa(String username, String password) {
		account Account = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tintuckhoacntt","root","");
			Statement sm = conn.createStatement();
			System.out.print("OK");
			String sql = "SELECT * FROM accounts WHERE username = '"+username+"' AND password = '"+password+"'";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next()) {
				Account = new account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return Account;
	}
}
