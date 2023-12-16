<%@page import="java.time.LocalDate"%>
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
        <title>Hồ sơ cá nhân</title>
        <script type="text/javascript">
        function validateForm() {
            var newPassword = document.getElementById("newPassword").value;
            var newPasswordComfirm = document.getElementById("newPasswordComfirm").value;
            var fullname = document.getElementById("fullname").value;
            if(fullname == "") {
                alert('Vui lòng nhập nội dung trước khi xác nhận.');
                return false;
            }
            else if(newPassword != newPasswordComfirm) {
            	alert('Mật khẩu không khớp.');
                return false;
            }
            return true;
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
         				<h2 class="myform__title">Thông tin tài khoản</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../account/update" class="myform-form" onsubmit="return validateForm()">
					    	<%
					    		String newPassword = (String)request.getSession().getAttribute("newPassword");
					    		if(newPassword != null) request.getSession().removeAttribute("newPassword");
						    	account Account = (account)request.getSession().getAttribute("AccountLogin");
								if(Account != null) {
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
					        		<input type="date" id="created_at" name="created_at" value="<%= Account.getCreated_at() %>" class="input_date" readonly/>
						        </div>
					        	<div class="myform_row">
					        		<label>Ngày cập nhật</label>
					        		<input type="date" id="updated_at" name="updated_at" value="<%= LocalDate.now() %>" readonly class="input_date"/>
					        	</div>
					        	<div class="myform_row myform_row__home">
					        		<label>Vai trò</label>
					        		<input type="text" id="role" name="role" value="<%= Account.getRole() %>" class="input_text" style="width: 70%" readonly/><br /><br />	        
					        	</div>
					        </div>
					        <input type="text" id="status" name="status" value="<%= Account.getStatus() %>" readonly class="input_text" hidden/><br /><br />
					        <br /><br />
					        <div class="myform_block">
					        	<div class="myform_row">
					        		<label>Mật khẩu mới</label>
					        		<input type="text" id="newPassword" name="newPassword" style="width: 50%" class="input_text"/>
					        	</div>
					        	<div class="myform_row">
					        		<label>Xác nhận mật khẩu mới</label>
					        		<input type="text" id="newPasswordComfirm" name="newPasswordComfirm" style="width: 50%" class="input_text"/>
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
