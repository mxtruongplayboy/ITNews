package controller.account;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.accountBO;
import model.Bean.account;

@WebServlet("/account/update")
public class AccountUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("updateId"));
    	accountBO accountBO = new accountBO();
    	account Account = accountBO.getAccount(id);
	    request.getSession().setAttribute("Account", Account);
	    response.sendRedirect("../admin/updateAccount.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("reset") != null) {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String role = request.getParameter("role");
			Date created_at = Date.valueOf(request.getParameter("created_at"));
			Date updated_at = Date.valueOf(request.getParameter("updated_at"));
			String status = request.getParameter("status");
			String newPassword = username;
	    	accountBO accountBO = new accountBO();
	    	accountBO.updatePassword(id, newPassword);
	    	account Account = new account(id, username, newPassword, fullname, role, created_at, updated_at, status);
	    	request.getSession().setAttribute("newPassword", newPassword);
	    	request.getSession().setAttribute("Account", Account);
		    response.sendRedirect("../admin/updateAccount.jsp");
	    }
		else {
			int id = Integer.parseInt(request.getParameter("id"));
	    	String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String newPassword = request.getParameter("newPassword");
			String role = request.getParameter("role");
			Date created_at = Date.valueOf(request.getParameter("created_at"));
			Date updated_at = Date.valueOf(request.getParameter("updated_at"));
			String status = request.getParameter("status");
	    	accountBO accountBO = new accountBO();
	    	accountBO.updateAccount(id, username, newPassword, fullname, role, created_at, updated_at, status);
	    	account Account = accountBO.getAccount(id);
	    	if (role == "Admin") {
	    		request.getSession().setAttribute("AccountLogin", Account);
	    	}
	    	List<account> listAccount = new ArrayList<>();
		    listAccount = accountBO.getAllaccount();
		    request.getSession().setAttribute("listAccount", listAccount);
		    response.sendRedirect("../admin/account.jsp");
		}	
	}

}
