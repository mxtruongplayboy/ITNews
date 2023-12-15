package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.accountBO;
import model.Bean.account;

@WebServlet("/C_Account")
public class C_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C_Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set character encoding for request
	    request.setCharacterEncoding("UTF-8");
	    
	    if(request.getParameter("requestadd") != null) {
		    response.sendRedirect("admin/addAccount.jsp");
	    }
	    else if(request.getParameter("add") != null) {
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
			    response.sendRedirect("admin/account.jsp");
	    	}
	    	else {
	    		request.getSession().setAttribute("error", "error");
	    		response.sendRedirect("admin/addAccount.jsp");
	    	}
	    } 
	    else if(request.getParameter("requestupdate") != null) {
	    	int id = Integer.parseInt(request.getParameter("updateId"));
	    	accountBO accountBO = new accountBO();
	    	account Account = accountBO.getAccount(id);
		    request.getSession().setAttribute("Account", Account);
		    response.sendRedirect("admin/updateAccount.jsp");
	    } 
	    else if(request.getParameter("update") != null) {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String role = request.getParameter("role");
			Date created_at = Date.valueOf(request.getParameter("created_at"));
			Date updated_at = Date.valueOf(request.getParameter("updated_at"));
			String status = request.getParameter("status");
	    	accountBO accountBO = new accountBO();
	    	accountBO.updateAccount(id, username, fullname, role, created_at, updated_at, status);
	    	List<account> listAccount = new ArrayList<>();
		    listAccount = accountBO.getAllaccount();
		    request.getSession().setAttribute("listAccount", listAccount);
		    response.sendRedirect("admin/account.jsp");
	    }
	    else if(request.getParameter("reset") != null) {
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
		    response.sendRedirect("admin/updateAccount.jsp");
	    }
	    else if(request.getParameter("delete") != null) {
	    	int id = Integer.parseInt(request.getParameter("deleteId"));
	    	accountBO accountBO = new accountBO();
	    	if(accountBO.checkAvailblePost(id)) {
	    		accountBO.deleteAccount(id);
	    	}
	    	else {
	    		request.getSession().setAttribute("errorID", request.getParameter("deleteId"));
	    	}
	    	List<account> listAccount = new ArrayList<>();
		    listAccount = accountBO.getAllaccount();
		    request.getSession().setAttribute("listAccount", listAccount);
		    response.sendRedirect("admin/account.jsp");
	    }
	    else
	    {
	    	accountBO accountBO = new accountBO();
		    List<account> listAccount = new ArrayList<>();
		    listAccount = accountBO.getAllaccount();
		    request.getSession().setAttribute("listAccount", listAccount);
		    response.sendRedirect("admin/account.jsp");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}	
}
