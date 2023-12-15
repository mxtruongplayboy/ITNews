<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Bean.categorie"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="../assets/fonts/stylesheet.css" />

        <link rel="stylesheet" href="../assets/css/reset.css" />
        <link rel="stylesheet" href="../assets/css/common.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <title>Quản lý danh mục</title>
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
	    	  window.location.href = '../category/create';
          }
	      
	      function showAnnouce() {
			  document.querySelector(".popup-annouce").classList.add("active");
		  }
				
		  function unshowAnnouce() {
			  document.querySelector(".popup-annouce").classList.remove("active");
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
                        <h3 class="table-title">Tất cả danh mục</h3>
                        <button class="btn success-btn" onclick="add()">Thêm</button>
                    </div>
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Mã danh mục</th>
                                <th>Tên danh mục</th>
                                <th>Slug</th>
                                <th>Ngày tạo</th>
                                <th>Ngày cập nhật</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>

                        <tbody>
                        	<%
								List<categorie> listCategorie = (List<categorie>)request.getSession().getAttribute("listCategorie");
								if(listCategorie != null) {
						      		request.getSession().removeAttribute("listCategorie");
									for(categorie Categorie : listCategorie) {
							%>
                            <tr>
                                <td><%= Categorie.getId() %></td>
                                <td><%= Categorie.getName() %></td>
                                <td><%= Categorie.getSlug() %></td>
                                <td><%= Categorie.getCreated_at() %></td>
                                <td><%= Categorie.getUpdated_at() %></td>
                                <td><%= Categorie.getStatus() %></td>
                                <td class="table-td-action">
                                    <button class="edit-btn table-btn" onclick="showConfirmUpdate(<%= Categorie.getId() %>)">
                                        <i
                                            class="fa-solid fa-pen-to-square table-btn-icon"
                                        ></i>
                                    </button>
                                    <button class="delete-btn table-btn" onclick="showConfirmDelete(<%= Categorie.getId() %>)">
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
      		<form method="POST" action="../category/delete" class="delete-form">
      			<input type="hidden" id="deleteId" name="deleteId" value="" />
        		<p>Bạn có chắc chắn xoá ID: <span id="deleteText"></span> ?</p>
		        <button type="submit" class="btn success-btn load-target" name="delete">Xác nhận</button>
		        <button type="reset" class="btn success-btn load-target" onclick="unshowConfirmDelete()">   Huỷ   </button>
		    </form>
      	</div>
      	
      	<div class="popup-update popup">
      		<form method="GET" action="../category/update" class="edit-form">
      			<input type="hidden" id="updateId" name="updateId" value="" />
        		<p>Bạn có muốn sửa ID: <span id="updateText"></span> ?</p>
		        <button type="submit" class="btn success-btn load-target" name="updateForm">Xác nhận</button>
		        <button type="reset" class="btn success-btn load-target" onclick="unshowConfirmUpdate()">   Huỷ   </button>
		    </form>
      	</div>	
      	
      	<div class="popup-annouce popup">
	      		<form method="POST" action="" class="annouce-form">
	        		<p>Danh mục đang chứa bài viết, không thể xoá !</p>
			        <button type="reset" class="btn success-btn " onclick="unshowAnnouce()">   Huỷ   </button>
			    </form>
      	</div>
      	<%
			String error = (String)request.getSession().getAttribute("error");
      		if(error != null) {
          		request.getSession().removeAttribute("error");
      	%>
	      	<script type="text/javascript">
	      	showAnnouce();
	      	</script>
      	<% } %>	
    </body>
</html>
