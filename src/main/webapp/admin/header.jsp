<%@page import="model.Bean.account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Insert title here</title>
    </head>
    <body>
        <section class="header sticky">
            <button
                type="button"
                class="header-nav-toggle"
                id="header-nav-toggle"
            >
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="18px"
                    height="18px"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                >
                    <line x1="3" y1="12" x2="21" y2="12"></line>
                    <line x1="3" y1="6" x2="21" y2="6"></line>
                    <line x1="3" y1="18" x2="21" y2="18"></line>
                </svg>
            </button>
            
            <div class="avatar-container">
                <div class="avatar">
                    <img src="../assets/images/user.png" alt="Avatar" />
                </div>
                <div class="dropdown-content">
                    <%
            			account AccountLogin = (account)request.getSession().getAttribute("AccountLogin");
        			%>
                	<% if(AccountLogin != null) { %>
                	<p><%= AccountLogin.getFullname() %></p>
                	<% } else { %>
                	<p>Admin</p>
                	<% } %>
                    <a href="#">Hồ sơ cá nhân</a>
                    <a href="../account/logout">Đăng xuất</a>
                </div>
            </div>
        </section>
    </body>
</html>
