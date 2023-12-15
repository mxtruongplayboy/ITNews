package controller.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.categorieBO;
import model.Bean.categorie;

@WebServlet("/category/create")
public class CategoryCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../admin/addCategorie.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    categorieBO bo = new categorieBO();
		String name = request.getParameter("name");
		String slug = request.getParameter("slug");
		String status = request.getParameter("status");
		if(bo.checkSlug(slug)) {
			bo.addCategorie(name, slug, status);
			List<categorie> listCategorie =  bo.getAllCategorie();
			request.getSession().setAttribute("listCategorie", listCategorie);
			response.sendRedirect("../admin/categorie.jsp");
		}
		else {
			categorie Categorie = new categorie(-1, name, slug, null, null, status);
			request.getSession().setAttribute("Categorie", Categorie);
			request.getSession().setAttribute("error", "error");
			response.sendRedirect("../admin/addCategorie.jsp");
		}
	}

}
