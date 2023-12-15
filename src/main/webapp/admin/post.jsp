<%@page import="model.Bean.post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Bean.account"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="../assets/fonts/stylesheet.css" />

        <link rel="stylesheet" href="../assets/css/reset.css" />
        <link rel="stylesheet" href="../assets/css/common.css" />
        <link rel="stylesheet" href="../assets/css/post.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <title>Quản lý bài viết</title>
        <script>
	      function showConfirmDelete(Id) {
	    	document.getElementById("deleteId").value = Id;
  			document.getElementById("deleteText").textContent = Id;
	        document.querySelector(".popup-delete").classList.add("active");
	      }
	
	      function unshowConfirmDelete() {
	        document.querySelector(".popup-delete").classList.remove("active");
	      }
	      
	      function showConfirmUpdate(Id) {
		    	document.getElementById("updateId").value = Id;
	  			document.getElementById("updateText").textContent = Id;
		        document.querySelector(".popup-update").classList.add("active");
		      }
		
		      function unshowConfirmUpdate() {
		        document.querySelector(".popup-update").classList.remove("active");
		      }
	      
	      function add() {
	    	  window.location.href = '../post/create';
          }
    </script>
    </head>
    <body>
        <%@include file="sidebar.jsp"%>
        <section class="page-content" id="page-content">
            <%@include file="header.jsp" %>
            <main class="main-content" id="main-content">
                <div class="table-block">
                    <div class="table-header">
                        <h3 class="table-title">Tất cả bài viết</h3>
                        <button class="btn success-btn" onclick="add()">Thêm</button>
                    </div>
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Mã bài viết</th>
                                <th>Tiêu đề</th>                   
                                <th>Ngày tạo</th>
                                <th>Ngày cập nhật</th>
                                <th>Trạng thái</th>
                                <th>Danh mục</th>
                                <th>Người tạo</th>
                                <th>Tin nổi bật</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>

                        <tbody>
                        	<%
								List<post> listPost = (List<post>)request.getSession().getAttribute("listPost");
								if(listPost != null) {
									for(post Post : listPost) {
							%>
                            <tr>
                                <td><%= Post.getId() %></td>
                                <td><%= Post.getTitle() %></td>
                                <td><%= Post.getCreated_at() %></td>
                                <td><%= Post.getUpdated_at() %></td>
                                <td><%= Post.getStatus() %></td>
                                <td><%= Post.getCategorieFK().getName() %></td>
                                <td><%= Post.getAccountFK().getFullname() %></td>
                                <td><%= Post.getHot() %></td>
                                <td class="table-td-action">
                                    <button class="edit-btn table-btn" onclick="showConfirmUpdate(<%= Post.getId() %>)">
                                        <i
                                            class="fa-solid fa-pen-to-square table-btn-icon"
                                        ></i>
                                    </button>
                                    <button class="delete-btn table-btn" onclick="showConfirmDelete(<%= Post.getId() %>)">
                                        <i
                                            class="fa-solid fa-trash table-btn-icon"
                                        ></i>
                                    </button>
                                </td>
                            </tr>
                            <%
									}
                            }
                            %>
                        </tbody>
                    </table> 
                </div>
            </main>
        </section>
       	
      	<div class="popup-delete popup">
      		<form method="POST" action="../C_Post" class="delete-form">
      			<input type="hidden" id="deleteId" name="deleteId" value="" />
        		<p>Bạn có chắc chắn xoá ID: <span id="deleteText"></span> ?</p>
		        <button type="submit" class="btn success-btn load-target" name="delete">Xác nhận</button>
		        <button type="reset" class="btn success-btn load-target" onclick="unshowConfirmDelete()">   Huỷ   </button>
		    </form>
      	</div>
      	
      	<div class="popup-update popup">
      		<form method="POST" action="../C_Account" class="edit-form">
      			<input type="hidden" id="updateId" name="updateId" value="" />
        		<p>Bạn có muốn sửa ID: <span id="updateText"></span> ?</p>
		        <button type="submit" class="btn success-btn load-target" name="requestupdate">Xác nhận</button>
		        <button type="reset" class="btn success-btn load-target" onclick="unshowConfirmUpdate()">   Huỷ   </button>
		    </form>
      	</div>
    </body>
</html>
