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

@WebServlet("/account/delete")
public class AccountDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    response.sendRedirect("../admin/account.jsp");
	}

}
