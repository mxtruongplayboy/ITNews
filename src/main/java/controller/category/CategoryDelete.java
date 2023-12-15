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

@WebServlet("/category/delete")
public class CategoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		response.sendRedirect("../admin/categorie.jsp");
	}

}
