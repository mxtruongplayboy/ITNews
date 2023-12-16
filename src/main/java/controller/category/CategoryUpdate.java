package controller.category;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.categorieBO;
import model.Bean.categorie;

@WebServlet("/category/update")
public class CategoryUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categorieBO bo = new categorieBO();
		int id = Integer.parseInt(request.getParameter("updateId"));
		categorie Categorie = bo.getCategorie(id);
		request.getSession().setAttribute("Categorie", Categorie);
		response.sendRedirect("../admin/updateCategorie.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		categorieBO bo = new categorieBO();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String slug = request.getParameter("slug");
		Date created_at = Date.valueOf(request.getParameter("created_at"));
		Date updated_at = Date.valueOf(request.getParameter("updated_at"));
		String status = request.getParameter("status");
		bo.updateCategorie(id, name, created_at, updated_at, status);
		List<categorie> listCategorie =  bo.getAllCategorie();
		request.getSession().setAttribute("listCategorie", listCategorie);
		response.sendRedirect("../admin/categorie.jsp");
	}

}
