<%@page import="model.Bean.account"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
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
        <title>Quản lý quản trị viên</title>
        <script>
	      function validateForm() {
              var username = document.getElementById("username").value;
              var password = document.getElementById("password").value;
              var fullname = document.getElementById("fullname").value;
              if(username == "" || fullname == "" || password == "") {
                  alert('Vui lòng nhập nội dung trước khi xác nhận.');
                  return false;
              }
              return true;
          }
	      
	      function cancel() {
	    	  window.location.href = '../account/getall';
	      }
	      
	      function getCurrentDate() {
	          const now = new Date();
	          const year = now.getFullYear();
	          const month = String(now.getMonth() + 1).padStart(2, '0');
	          const day = String(now.getDate()).padStart(2, '0');
	          return `${year}-${month}-${day}`;
	      }
    </script>
    </head>
    <body>
    	<%
    		if((account)request.getSession().getAttribute("AccountLogin") == null) {
    			response.sendRedirect("../account/checkLogin");
    		}
        %>
        <%@include file="sidebar.jsp"%>
        <section class="page-content" id="page-content">
            <%@include file="header.jsp" %>
            <main class="main-content" id="main-content">   
	            <div class="table-block">        
         			<div class="myform">
         				<h2 class="myform__title">Cập nhật thông tin quản trị viên</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../account/update" class="myform-form" onsubmit="return validateForm()">
					    	<%
					    		String newPassword = (String)request.getSession().getAttribute("newPassword");
					    		if(newPassword != null) request.getSession().removeAttribute("newPassword");
						    	account Account = (account)request.getSession().getAttribute("Account");
								if(Account != null) {
									request.getSession().removeAttribute("Account");
					    	%>
					    	<label>Mã tài khoản</label>
					        <input type="text" id="id" name="id" value="<%= Account.getId() %>" readonly class="input_text"/><br /><br />
					        <label>Tên tài khoản</label>
					        <input type="text" id="username" name="username" value="<%= Account.getUsername() %>" class="input_text" readonly/><br /><br />	        
					        <label>Họ và tên</label>
					        <input type="text" id="fullname" name="fullname" value="<%= Account.getFullname() %>" class="input_text"/><br /><br />		        
					        <div class="myform_block">
						        <div class="myform_row">
						        	<label>Ngày tạo</label>
					        		<input type="date" id="created_at" name="created_at" value="<%= Account.getCreated_at() %>" class="input_date"/>
						        </div>
					        	<div class="myform_row">
					        		<label>Ngày cập nhật</label>
					        		<input type="date" id="updated_at" name="updated_at" value="<%= LocalDate.now() %>" readonly class="input_date"/>
					        	</div>
					        </div>
					        <br /><br />
					        <div class="myform_block"> 
					        	<div class="myform_row">
					        		<label>Trạng thái</label>
							        <select name="status" class="myform__status">
							        	<% if(Account.getStatus().equals("Hoạt động")) {%>
								            <option value="Hoạt động" selected>Hoạt động</option>
								            <option value="Không hoạt động">Không hoạt động</option>
								        <% } else {%>
								        	<option value="Hoạt động" >Hoạt động</option>
								            <option value="Không hoạt động" selected>Không hoạt động</option>
								        <% } %>
							        </select>					   
					        	</div>
					        	<div class="myform_row">
					        		<label>Vai trò</label>
							        <select name="role" class="myform__status">
							        	<% if(Account.getRole().equals("Admin")) {%>
								            <option value="Admin" selected>Admin</option>
								            <option value="SuperAdmin">Super Admin</option>
								        <% } else {%>
								        	<option value="Admin">Admin</option>
								            <option value="SuperAdmin" selected>Super Admin</option>
								        <% } %>
							        </select>
					        	</div>
					        </div>
					        <br /><br />
					        <div class="myform_block">
						        <div class="myform_row">
						        	<button type="submit" class="btn success-btn load-target" name="reset">Khôi phục mật khẩu</button>
						        </div>
					        	<div class="myform_row">
					        		<label>Mật khẩu mới</label>
					        		<% if(newPassword != null) { %>
					        			<input type="text" id="newPassword" name="newPassword" class="input_text" style="width: 20%" value="<%= newPassword %>" readonly/>
					        		<% } else  {%>
					        			<input type="text" id="newPassword" name="newPassword" class="input_text" style="width: 20%" readonly/>
					        		<% } %>
					        	</div>
					        </div>
					        <div class="myform_footer">
					        	<div class="myform__line"></div>
						        <div class="myform__funtion">
						            <button type="submit" class="btn success-btn load-target" name="update">Xác nhận</button>
					             	<button type="reset" class="btn success-btn load-target" onclick="cancel()">   Huỷ   </button>
						        </div>
					        </div>
					        <%
									}
					        %>
					    </form>
					</div>
                </div>
            </main>
        </section>
    </body>
</html>
