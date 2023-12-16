<%@page import="model.Bean.PostDetailVM"%>
<%@page import="model.Bean.PostDescVM"%>
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

    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/homePage.css">
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
         		if (categoryId == 0){
         	%>
            <div class="news-container">
            	<%
            	List<PostDescVM> listPosts = (List<PostDescVM>)request.getSession().getAttribute("posts");
            	%>
                <div class="left-container">
                	<%
                		if (listPosts != null && listPosts.size() != 0)
                		{
                	%>
                    <article class="hot-news">
	                    <div class="hot-news__img-container">
	                        <a href="./detail?postId=<%=listPosts.get(0).getId() %>">
	                        	<img src="<%=listPosts.get(0).getImgUrl() %>" class="hot-news__img" alt="">
	                        </a>
	                    </div>
                        <h2 class="hot-news__title">
	                        <a href="./detail?postId=<%=listPosts.get(0).getId() %>">
	                        	<%=listPosts.get(0).getTitle() %>
	                        </a>
	                        <%
                        		if(listPosts.get(0).isHot() == true){
	                        %>
	                        <span class="hot-news__tag">HOT</span>
	                        <%
	                        	}
	                        %>
	                        <%
                        		if(listPosts.get(0).isLatest() == true){
	                        %>
	                        <span class="latest-news__tag">NEW</span>
	                        <%
	                        	}
	                        %>
                        </h2>
                        <span class="hot-news__date">Ngày đăng: <%=listPosts.get(0).getCreationDate() %></span>
                    </article>
                    <%
                		}
                    %>
                    
                    <div class="hot-latest-news__list">
						<%
							if (listPosts != null){
								for(int i=1; i < (int)Math.min(4, listPosts.size());i++)
								{
									PostDescVM item = listPosts.get(i);
						%>
                        <article class="hot-latest-news">
                            <div class="hot-latest-news__img-container">
                                <a href="./detail?postId=<%=item.getId() %>">
                                    <img src="<%=item.getImgUrl() %>" class="hot-latest-news__img" alt="">
                                </a>
                            </div>
                            <h2 class="hot-latest-news__title">
                                <a href="./detail?postId=<%=item.getId() %>"><%=item.getTitle() %></a>
                                <%
                        			if(item.isHot() == true){
		                        %>
		                        <span class="hot-news__tag">HOT</span>
		                        <%
		                        	}
		                        %>
		                        <%
	                        		if(item.isLatest() == true){
		                        %>
		                        <span class="latest-news__tag">NEW</span>
		                        <%
		                        	}
		                        %>
                            </h2>
                            <span class="hot-latest-news__date"><%=item.getCreationDate() %></span>
                        </article>
                        <%
								}
							}
                        %>
                    </div>
                </div>
                <div class="right-container">
                    <ul class="news-list">
                        <%
                        	if (listPosts != null){
                  				for (int i=4;i<listPosts.size();i++){
                  					PostDescVM item = listPosts.get(i);
                        %>
                        <li class="news-item">
                            <a href="./detail?postId=<%=item.getId() %>" class="news-item__link">
                                <img src="<%=item.getImgUrl() %>" alt="" class="news-item__img">
                            </a>
                            <div class="content">
                                <h3 class="news-item__title">
                                	<a href="./detail?postId=<%=item.getId() %>"><%=item.getTitle() %></a>
                                	<%
	                        			if(item.isHot() == true){
			                        %>
			                        <span class="hot-news__tag">HOT</span>
			                        <%
			                        	}
			                        %>
			                        <%
		                        		if(item.isLatest() == true){
			                        %>
			                        <span class="latest-news__tag">NEW</span>
			                        <%
			                        	}
			                        %>
                                </h3>
                                <span class="news-item__date">Ngày đăng: <%=item.getCreationDate() %></span>
                                <div class="news-item__desc">Ngày 05/12/2023, tại văn phòng Khoa CN Thông tin - trường Đại học Bách khoa– ĐHĐN đã tổ chức Hội nghị sinh viên nghiên cứu khoa học năm học 2023-2024. Tham gia hội nghị có tổng số 11 đề tài thuộc nhiều lĩnh vực Công nghệ Thông tin</div>
                            </div>
                        </li>
                        <%
                  				}
                        	}
                        %>
                    </ul>
                </div>
            </div>
               <%
           		} else {
               %>
               <ul class="news-list">
                   <%
                   List<PostDescVM> listPosts = (List<PostDescVM>)request.getSession().getAttribute("posts");
                   	if (listPosts != null){
             				for (int i=0;i<listPosts.size();i++){
             					PostDescVM item = listPosts.get(i);
                   %>
                   <li class="news-item">
                       <a href="./detail?postId=<%=item.getId() %>" class="news-item__link">
                           <img src="<%=item.getImgUrl() %>" alt="" class="news-item__img">
                       </a>
                       <div class="content">
                           <h3 class="news-item__title">
                           		<a href="./detail?postId=<%=item.getId() %>"><%=item.getTitle() %></a>
                           		<%
                        			if(item.isHot() == true){
		                        %>
		                        <span class="hot-news__tag">HOT</span>
		                        <%
		                        	}
		                        %>
		                        <%
	                        		if(item.isLatest() == true){
		                        %>
		                        <span class="latest-news__tag">NEW</span>
		                        <%
		                        	}
		                        %>
                           </h3>
                           <span class="news-item__date">Ngày đăng: <%=item.getCreationDate() %></span>
                           <div class="news-item__desc">Ngày 05/12/2023, tại văn phòng Khoa CN Thông tin - trường Đại học Bách khoa– ĐHĐN đã tổ chức Hội nghị sinh viên nghiên cứu khoa học năm học 2023-2024. Tham gia hội nghị có tổng số 11 đề tài thuộc nhiều lĩnh vực Công nghệ Thông tin</div>
                       </div>
                   </li>
                   <%
             				}
                   	}
                   %>
               </ul>
             <%
       		}
            %>
        </div>
    </section>
</body>
</html>