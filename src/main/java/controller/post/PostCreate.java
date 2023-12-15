package controller.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.categorieBO;
import model.Bean.categorieFK;

@WebServlet("/post/create")
public class PostCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categorieBO categorieBO = new categorieBO();
    	List<categorieFK> listCategorieFK = new ArrayList<>();
    	listCategorieFK = categorieBO.getAllCategorieFK();
    	request.getSession().setAttribute("listCategorieFK", listCategorieFK);
    	response.sendRedirect("../admin/addPost.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
