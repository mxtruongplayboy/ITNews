package controller.post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.FileManager;
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
		post p = postBO.getPostByID(id);
		if (postBO.deletePost(id) == true) {
			String fileName = p.getImage();
			File filePath = new File(FileManager.basePath + "\\assets\\imagePosts\\" + fileName);
			if (filePath.delete() == true)
				System.out.println("Delete successfully");
		}
	    response.sendRedirect("../post/getAll");
	}
}
