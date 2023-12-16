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
import model.Bean.PostDescRequest;
import model.Bean.PostDescVM;
import model.Bean.categorieFK;

/**
 * Servlet implementation class PostHomePage
 */
@WebServlet("/home/index")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get all categories
		categorieBO cBO = new categorieBO();
		List<categorieFK> listCatergorie = cBO.getAllCategorieFK();
		HttpSession session = request.getSession();
		session.setAttribute("categories", listCatergorie);
		
		// Set category id
		PostDescRequest postDescRequest = new PostDescRequest("", 0, 10);
		String categoryId = request.getParameter("categoryId");
		if (categoryId != null) {
			try {
				postDescRequest.setCategoryId(Integer.parseInt(categoryId));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		// Set keyword
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			try {
				postDescRequest.setKeyword(keyword);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		// Get all news
		postBO post_bo = new postBO();
		List<PostDescVM> listPosts = post_bo.getAllPostDescVM(postDescRequest);
		session.setAttribute("posts", listPosts);
		session.setAttribute("categoryId", postDescRequest.getCategoryId());
		session.setAttribute("keyword", postDescRequest.getKeyword());
		
		response.sendRedirect("../home/index.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
