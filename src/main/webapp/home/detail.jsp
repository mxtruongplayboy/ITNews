<%@page import="model.Bean.PostDetailVM"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Bean.categorieFK" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tin tức khoa</title>

    <link rel="stylesheet" href="../assets/css/detailPage.css">
    <link rel="stylesheet" 
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" 
    	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" 
    	crossorigin="anonymous" 
    	referrerpolicy="no-referrer" 
    />
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="header__main">
                <div class="header__logo">
                    <a href="../home/index"><img src="https://dut.udn.vn/portals/_default/skins/dhbk/img/front/logo.png" alt="Logo"></a>
                </div>
                <div class="header__search">
                    <form action="./index" method="get" class="form__search">
                    	<%
                    		if (request.getSession().getAttribute("categoryId") != null)
                    		{
                    			int categoryId = (int)request.getSession().getAttribute("categoryId");
                    	%>
                    		<input type="text" name="categoryId" value="<%=categoryId %>" hidden="hidden" />
                    	<%
                    		}
                    	%>
                    	<%
                    		String keyword = (String)request.getSession().getAttribute("keyword");
                    		if (keyword != null){
                    	%>
                        	<input type="text" name="keyword" value="<%=keyword %>" placeholder="Tìm kiếm">
                        <%
                    		} else {
                        %>
                        <input type="text" name="keyword" value="" placeholder="Tìm kiếm">
                        <%
                    		}
                        %>
                        <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>
                <div class="header__admin">
                    <a href="../admin/login.jsp" class="header__login-btn">Đăng nhập</a>
                </div>
            </div>
        </div>
    </header>
    <section class="category">
        <div class="container">
            <div class="category__main">
                <ul class="category__main-list">
                    <%
                    	int categoryId = 0;
                    	if (request.getSession().getAttribute("categoryId") != null){
                    		categoryId = (int)request.getSession().getAttribute("categoryId");
                    	}
                    	
                    	String className = "category__main-item__link--active";
                    %>
                    <li class="category__main-item">
                       	<a href="./index" class="category__main-item__link <%=(categoryId == 0)?className:"" %>">Nổi bật</a>
                   	</li>
                    <%
                    	List<categorieFK> list = (List<categorieFK>)request.getSession().getAttribute("categories");
						if (list != null){
							request.getSession().removeAttribute("categories");
							for(categorieFK item : list){
					%>
						<li class="category__main-item">
                        	<a href="./index?categoryId=<%= item.getId() %>" class="category__main-item__link <%=(categoryId == item.getId())?className:"" %>"><%= item.getName() %></a>
                    	</li>
					<%
							}
						}
					%>
                </ul>
            </div>
        </div>
    </section>
    <section class="news">
    	<div class="container">
	    	<%
	        	PostDetailVM postDetailVM = (PostDetailVM)request.getSession().getAttribute("postDetailVM");
	        	if (postDetailVM != null){
	        %>
	        <h1 class="news__title"><%=postDetailVM.getTitle() %></h1>
	        <%=postDetailVM.getContent() %>
	        <span class="news__creationDate"><%=postDetailVM.getCreationDate() %></span>
	        <%
	        	}
	        %>
    	</div>
    </section>
</body>
</html>