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

@WebServlet("/category/getAll")
public class CategoryGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryGetAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categorieBO bo = new categorieBO();
		List<categorie> listCategorie =  bo.getAllCategorie();
		request.getSession().setAttribute("listCategorie", listCategorie);
		response.sendRedirect("../admin/categorie.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
