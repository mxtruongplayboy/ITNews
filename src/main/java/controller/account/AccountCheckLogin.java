package controller.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.accountBO;
import model.Bean.account;

@WebServlet("/account/checkLogin")
public class AccountCheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountCheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../admin/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		accountBO accountBO = new accountBO();
		account Account = accountBO.getAccountByUsPa(username, password);
		if(Account != null) {
			if(Account.getStatus().equals("Hoạt động")) {
				request.getSession().setAttribute("AccountLogin", Account);
				response.sendRedirect("../admin/home.jsp");
			}
			else {
				request.getSession().setAttribute("error", "Block");
				response.sendRedirect("../admin/login.jsp");
			}
		}
		else {
			request.getSession().setAttribute("error", "NotValid");
			response.sendRedirect("../admin/login.jsp");
		}
	}

}
