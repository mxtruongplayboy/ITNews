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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <title>Quản lý tài khoản</title>
        
        <link rel="icon" href="../assets/logo/logo-small.png" type="image/png">
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
		  
		  function showAnnouce() {
			document.querySelector(".popup-annouce").classList.add("active");
		  }
			
		  function unshowAnnouce() {
			document.querySelector(".popup-annouce").classList.remove("active");
		  }
	      
	      function add() {
	    	  window.location.href = '../account/create';
          }
    </script>
    </head>
    <body>
    	<%
    		if((account)request.getSession().getAttribute("AccountLogin") == null) {
    			response.sendRedirect("../account/checkLogin");
    		}
    		else if(((account)request.getSession().getAttribute("AccountLogin")).getRole().equals("Admin")) {
				response.sendRedirect("../home/homeAdmin");
			}
        %>
        <%@include file="sidebar.jsp"%>
        <section class="page-content" id="page-content">
            <%@include file="header.jsp" %>
            <main class="main-content" id="main-content">
                <div class="table-block">
                    <div class="table-header">
                        <h3 class="table-title">Tất cả tài khoản</h3>
                        <button class="btn success-btn" onclick="add()">Thêm</button>
                    </div>
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Mã tài khoản</th>
                                <th>Tên tài khoản</th>
                                <th>Họ và tên</th>
                                <th>Vai trò</th>
                                <th>Ngày tạo</th>
                                <th>Ngày cập nhật</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>

                        <tbody>
                        	<%
								List<account> listAccounts = (List<account>)request.getSession().getAttribute("listAccount");
								if(listAccounts != null) {
						      		request.getSession().removeAttribute("listAccount");
									for(account Account : listAccounts) {
							%>
                            <tr>
                                <td><%= Account.getId() %></td>
                                <td><%= Account.getUsername() %></td>
                                <td><%= Account.getFullname() %></td>
                                <td><%= Account.getRole() %></td>
                                <td><%= Account.getCreated_at() %></td>
                                <td><%= Account.getUpdated_at() %></td>
                                <td><%= Account.getStatus() %></td>
                                <td class="table-td-action">
                                    <% if(Account.getRole().equals("Admin")) { %> 
                                    <button class="edit-btn table-btn" onclick="showConfirmUpdate(<%= Account.getId() %>)">
                                        <i
                                            class="fa-solid fa-pen-to-square table-btn-icon"
                                        ></i>
                                    </button>
                                    <button class="delete-btn table-btn" onclick="showConfirmDelete(<%= Account.getId() %>)">
                                        <i
                                            class="fa-solid fa-trash table-btn-icon"
                                        ></i>
                                    </button>
                                    <% } %>
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
      		<form method="POST" action="../account/delete" class="delete-form">
      			<input type="hidden" id="deleteId" name="deleteId" value="" />
        		<p>Bạn có chắc chắn xoá ID: <span id="deleteText"></span> ?</p>
		        <button type="submit" class="btn success-btn " name="delete">Xác nhận</button>
		        <button type="reset" class="btn success-btn " onclick="unshowConfirmDelete()">   Huỷ   </button>
		    </form>
      	</div>
      	
      	<div class="popup-update popup">
      		<form method="GET" action="../account/update" class="edit-form">
      			<input type="hidden" id="updateId" name="updateId" value="" />
        		<p>Bạn có muốn sửa ID: <span id="updateText"></span> ?</p>
		        <button type="submit" class="btn success-btn " name="requestupdate">Xác nhận</button>
		        <button type="reset" class="btn success-btn " onclick="unshowConfirmUpdate()">   Huỷ   </button>
		    </form>
      	</div>
      	
      	<div class="popup-annouce popup">
	      		<form method="POST" action="" class="annouce-form">
	        		<p>Quản trị viên đang có bài viết, không thể xoá ?</p>
			        <button type="reset" class="btn success-btn " onclick="unshowAnnouce()">   Huỷ   </button>
			    </form>
      	</div>
      	<%
			String errorID = (String)request.getSession().getAttribute("errorID");
      		if(errorID != null) {
          		request.getSession().removeAttribute("errorID");
      	%>
	      	<script type="text/javascript">
	      	showAnnouce();
	      	</script>
      	<% } %>	
    </body>
</html>
