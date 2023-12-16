package controller.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import model.BO.postBO;
import model.Bean.account;
import model.Bean.post;

@WebServlet("/post/getAll")
public class PostGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostGetAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		account Account = (account)request.getSession().getAttribute("AccountLogin");
		postBO postBO = new postBO();
	    List<post> listPost = new ArrayList<>();
		if(Account.getRole().equals("Admin")) {
			listPost = postBO.getAllpostByAccountID(Account.getId());
		}
		else {
			listPost = postBO.getAllpost();
		} 
	    request.getSession().setAttribute("listPost", listPost);
	    response.sendRedirect("../admin/post.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
