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
import model.BO.postBO;
import model.Bean.categorieFK;
import model.Bean.post;

@WebServlet("/post/update")
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("updateId"));
		System.out.println(id);
		postBO bo = new postBO();
		post Post = bo.getPostByID(id);
		request.getSession().setAttribute("Post", Post);
		categorieBO categorieBO = new categorieBO();
    	List<categorieFK> listCategorieFK = new ArrayList<>();
    	listCategorieFK = categorieBO.getAllCategorieFK();
    	request.getSession().setAttribute("listCategorieFK", listCategorieFK);
		response.sendRedirect("../admin/updatePost.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
