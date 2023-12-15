package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.categorieBO;
import model.BO.postBO;
import model.Bean.categorieFK;
import model.Bean.post;


@WebServlet("/C_Post")
public class C_Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C_Post() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set character encoding for request
	    request.setCharacterEncoding("UTF-8");
	    
	    if(request.getParameter("requestadd") != null) {
	    	categorieBO categorieBO = new categorieBO();
	    	List<categorieFK> listCategorieFK = new ArrayList<>();
	    	listCategorieFK = categorieBO.getAllCategorieFK();
	    	request.getSession().setAttribute("listCategorieFK", listCategorieFK);
	    	response.sendRedirect("admin/addPost.jsp");
	    }
	    else {
	    	postBO postBO = new postBO();
		    List<post> listPost = new ArrayList<>();
		    listPost = postBO.getAllpost();
		    request.getSession().setAttribute("listPost", listPost);
		    response.sendRedirect("admin/post.jsp");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
