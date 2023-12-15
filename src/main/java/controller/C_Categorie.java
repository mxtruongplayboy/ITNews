package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.categorieBO;
import model.Bean.categorie;

@WebServlet("/C_Categorie")
public class C_Categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C_Categorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set character encoding for request
	    request.setCharacterEncoding("UTF-8");
	    
	    if(request.getParameter("requestadd") != null) {
			response.sendRedirect("admin/addCategorie.jsp");
		}
	    else if(request.getParameter("add") != null) {
			categorieBO bo = new categorieBO();
			String name = request.getParameter("name");
			String slug = request.getParameter("slug");
			String status = request.getParameter("status");
			if(bo.checkSlug(slug)) {
				bo.addCategorie(name, slug, status);
				List<categorie> listCategorie =  bo.getAllCategorie();
				request.getSession().setAttribute("listCategorie", listCategorie);
				response.sendRedirect("admin/categorie.jsp");
			}
			else {
				categorie Categorie = new categorie(-1, name, slug, null, null, status);
				request.getSession().setAttribute("Categorie", Categorie);
				request.getSession().setAttribute("error", "error");
				response.sendRedirect("admin/addCategorie.jsp");
			}
		}
		else if(request.getParameter("delete") != null) {
			categorieBO bo = new categorieBO();
			int id = Integer.parseInt(request.getParameter("deleteId"));
			if(bo.checkAvailblePost(id)) {
				bo.deleteCategorie(id);
			}
			else {
				request.getSession().setAttribute("error", "error");
			}
			List<categorie> listCategorie =  bo.getAllCategorie();
			request.getSession().setAttribute("listCategorie", listCategorie);
			response.sendRedirect("admin/categorie.jsp");
		}
		else if(request.getParameter("updateForm") != null) {
			categorieBO bo = new categorieBO();
			int id = Integer.parseInt(request.getParameter("updateId"));
			categorie Categorie = bo.getCategorie(id);
			request.getSession().setAttribute("Categorie", Categorie);
			response.sendRedirect("admin/updateCategorie.jsp");
		}
		else if(request.getParameter("update") != null) {
			categorieBO bo = new categorieBO();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String slug = request.getParameter("slug");
			Date created_at = Date.valueOf(request.getParameter("created_at"));
			Date updated_at = Date.valueOf(request.getParameter("updated_at"));
			String status = request.getParameter("status");
			if(bo.checkSlug(slug)) {
				bo.updateCategorie(id, name, slug, created_at, updated_at, status);
				List<categorie> listCategorie =  bo.getAllCategorie();
				request.getSession().setAttribute("listCategorie", listCategorie);
				response.sendRedirect("admin/categorie.jsp");
			}
			else {
				categorie Categorie = new categorie(id, name, slug, created_at, updated_at, status);
				request.getSession().setAttribute("Categorie", Categorie);
				request.getSession().setAttribute("error", "error");
				response.sendRedirect("admin/updateCategorie.jsp");
			}
		}
		else {
			categorieBO bo = new categorieBO();
			List<categorie> listCategorie =  bo.getAllCategorie();
			request.getSession().setAttribute("listCategorie", listCategorie);
			response.sendRedirect("admin/categorie.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
