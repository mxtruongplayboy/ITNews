package controller.home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BO.categorieBO;
import model.BO.postBO;
import model.Bean.PostDetailVM;
import model.Bean.categorieFK;

/**
 * Servlet implementation class PostDetail
 */
@WebServlet("/home/detail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("postId");
		if (id != null) {
			try {
				// Get posts
				postBO bo = new postBO();
				PostDetailVM postDetailVM = bo.getPostDetailById(Integer.parseInt(id));
				HttpSession session = request.getSession();
				session.setAttribute("postDetailVM", postDetailVM);
				
				// Get all categories
				categorieBO cBO = new categorieBO();
				List<categorieFK> listCatergorie = cBO.getAllCategorieFK();
				session.setAttribute("categories", listCatergorie);
				
				response.sendRedirect("../home/detail.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("../home/index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
