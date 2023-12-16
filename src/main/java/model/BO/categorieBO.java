package model.BO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Bean.categorie;
import model.Bean.categorieFK;
import model.DAO.categorieDAO;
import model.DAO.postDAO;

public class categorieBO {
	public List<categorie> getAllCategorie() {
		categorieDAO categorieDAO = new categorieDAO();
		return categorieDAO.getAllCategorie();
	}
	
	public void addCategorie(String name, String slug, String status) {
		categorieDAO categorieDAO = new categorieDAO();
		categorieDAO.addCategorie(name, slug, status);
	}
	
	public void deleteCategorie(int id) {
		categorieDAO categorieDAO = new categorieDAO();
		categorieDAO.deleteCategorie(id);
	}
	
	public categorie getCategorie(int id) {
		categorieDAO categorieDAO = new categorieDAO();
		return categorieDAO.getCategorie(id);
	}
	
	public void updateCategorie(int id, String name, Date created_at, Date updated_at, String status) {
		categorieDAO categorieDAO = new categorieDAO();
		categorieDAO.updateCategorie(id,name,created_at,updated_at,status);
	}
	
	public List<categorieFK> getAllCategorieFK() {
		categorieDAO categorieDAO = new categorieDAO();
		List<categorieFK> list = new ArrayList<>();
		list = categorieDAO.getAllCategorieFK();
		return list;
	}
		
	public boolean checkAvailblePost(int id) {
		postDAO postDAO = new postDAO();
		if(postDAO.getFirstPostByCategorieID(id) == null) return true;
		return false;
	}
}
