package model.BO;

import java.sql.Date;
import java.util.List;

import model.Bean.account;
import model.DAO.accountDAO;
import model.DAO.postDAO;



public class accountBO {
	public List<account> getAllaccount() {
		accountDAO accountDAO = new accountDAO();
		return accountDAO.getAllaccount();
	}
	
	public void addAccount(String username, String password, String fullname, String role, String status) {
		accountDAO accountDAO = new accountDAO();
		accountDAO.addAccount(username, password, fullname, role, status);
	}
	
	public void deleteAccount(int id) {
		accountDAO accountDAO = new accountDAO();
		accountDAO.deleteAccount(id);
	}
	
	public void updateAccount(int id, String username, String fullname, String role, Date created_at, Date updated_at, String status) {
		accountDAO accountDAO = new accountDAO();
		accountDAO.updateAccount(id, username, fullname, role, created_at, updated_at, status);
	}
	
	public account getAccount(int id) {
		accountDAO accountDAO = new accountDAO();
		return accountDAO.getAccount(id);
	}
	
	public void updatePassword(int id, String password) {
		accountDAO accountDAO = new accountDAO();
		accountDAO.updatePassword(id, password);
	}
	
	public boolean checkAvailblePost(int id) {
		postDAO postDAO = new postDAO();
		if(postDAO.getFirstPostByAccountID(id) == null) return true;
		return false;
	}
	
	public boolean checkUsername(String username) {
		accountDAO accountDAO = new accountDAO();
		if(accountDAO.checkUsername(username)) return true;
		return false;
	}
	
	public account getAccountByUsPa(String username, String password) {
		accountDAO accountDAO = new accountDAO();
		return accountDAO.getAccountByUsPa(username, password);
	}
}
