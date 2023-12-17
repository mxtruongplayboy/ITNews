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
        <section class="sidebar" id="sidebar">
            <a href="../home/homeAdmin" class="brand">
                <img
                    class="brand__logo"
                    src="../assets/images/avatar.jpg"
                    alt="Logo-ITFaculty"
                />
                <p class="brand__text">Quản lý tin tức</p>
            </a>
            <%
            	account acc = null;
            	if ((account)request.getSession().getAttribute("AccountLogin") != null){
            		acc = (account)request.getSession().getAttribute("AccountLogin");
            	}
            %>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="../home/homeAdmin" class="nav-link">
                        <i class="fa-solid fa-house nav-icon"></i>
                        Hồ sơ cá nhân
                    </a>
                </li>
                <%
               		if (acc!= null && acc.getRole().equals("SuperAdmin") == true) {
               	%>
                <li class="nav-item">
                    <a class="nav-link" href="../category/getAll">                
                        <i class="fa-solid fa-list nav-icon"></i>
                        Danh mục
                    </a>
                </li>
				<%
               		}
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../post/getAll">
                        <i class="fa-solid fa-newspaper nav-icon"></i>
                        Tin tức
                    </a>
                </li>
				<%
					if (acc!=null && acc.getRole().equals("SuperAdmin") == true){
				%>
                <li class="nav-item">
                    <a class="nav-link" href="../account/getall">
                        <i class="fa-solid fa-users nav-icon"></i>
                        Quản trị viên
                    </a>
                </li>
                <% } %>
            </ul>
        </section>
    </body>
</html>
