package controller.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.accountBO;
import model.Bean.account;

@WebServlet("/account/create")
public class AccountCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("../admin/addAccount.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String role = request.getParameter("role");
		String status = request.getParameter("status");
    	accountBO accountBO = new accountBO();
    	if(accountBO.checkUsername(username)) {
	    	accountBO.addAccount(username, password, fullname, role, status);
	    	List<account> listAccount = new ArrayList<>();
		    listAccount = accountBO.getAllaccount();
		    request.getSession().setAttribute("listAccount", listAccount);
		    response.sendRedirect("../admin/account.jsp");
    	}
    	else {
    		request.getSession().setAttribute("error", "error");
    		response.sendRedirect("../admin/addAccount.jsp");
    	}
	}

}
