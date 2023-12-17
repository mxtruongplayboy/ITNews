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
        <link rel="icon" href="../assets/logo/logo-small.png" type="image/png">
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
	      
	      function lastUsernameChange() { 
	    	  var lastUsername = document.getElementById("lastUsername");
	    	  if(lastUsername.value.toString().length > 6) lastUsername.value = lastUsername.value.toString().substring(0, 6);
	    	  if(lastUsername.value.toString().length != 0) document.getElementById("username").value = document.getElementById("firstUsername").value + lastUsername.value;
	    	  else document.getElementById("username").value = "";
	    	  document.getElementById("password").value = document.getElementById("username").value;
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
         			<div class="myform">
         				<h2 class="myform__title">Thêm quản trị viên</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../account/create" class="myform-form" onsubmit="return validateForm()">
					        <label>Tên tài khoản</label>
					        <div class="myform_block">
					        	<input type="text" id="firstUsername" name="firstUsername" class="input_text" value="admin" readonly style="width: 30%"/><br /><br />
					        	<input type="number" id="lastUsername" name="lastUsername" class="input_text" placeholder="xxxxxx" oninput="lastUsernameChange()"/><br /><br />
					        	<input type="text" id="username" name="username" class="input_text" hidden/><br /><br />
					        </div>				        
					        <label>Mật khẩu</label>
					        <input type="text" id="password" name="password" class="input_text" readonly/><br /><br />
					        <label>Họ và tên</label>
					        <input type="text" id="fullname" name="fullname" class="input_text"/><br /><br />
					        <div class="myform_block">
						        <div class="myform_row">
						        	<label>Ngày tạo</label>
					        		<input type="date" id="created_at" name="created_at" value="<%= LocalDate.now() %>" readonly class="input_date"/>
						        </div>
					        	<div class="myform_row">
					        		<label>Ngày cập nhật</label>
					        		<input type="date" id="updated_at" name="updated_at" value="NULL" readonly class="input_date"/>
					        	</div>
					        </div>   
					        <br /><br />
					        <div class="myform_block"> 
					        	<div class="myform_row">
					        		<label>Trạng thái</label>
							        <select name="status" class="myform__status">
								            <option value="Hoạt động">Hoạt động</option>
								            <option value="Không hoạt động">Không hoạt động</option>
							        </select>					   
					        	</div>
					        	<div class="myform_row">
					        		<label>Vai trò</label>
							        <select name="role" class="myform__status">
								        	<option value="Admin">Admin</option>
								            <option value="SuperAdmin">Super Admin</option>
							        </select>
					        	</div>
					        </div>
					            <br /><br />
					        <div class="myform_footer">
					        	<div class="myform__line"></div>
						        <div class="myform__funtion">
						             <button type="submit" class="btn success-btn load-target" name="add">Xác nhận</button>
						             <button type="reset" class="btn success-btn load-target" onclick="cancel()">   Huỷ   </button>
						        </div>
					        </div>
					    </form>
					</div>
                </div>
            </main>
        </section>
        
        <div class="popup-annouce popup">
	      		<form method="POST" action="" class="annouce-form">
	        		<p>Tên tài khoản đã tồn tại, hãy chọn tên khác</p>
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
