package controller.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.postBO;
import model.Bean.post;

@WebServlet("/post/delete")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("deleteId"));
		postBO postBO = new postBO();
		postBO.deletePost(id);
		List<post> listPost = new ArrayList<>();
	    listPost = postBO.getAllpost();
	    request.getSession().setAttribute("listPost", listPost);
	    response.sendRedirect("../admin/post.jsp");
	}
}
