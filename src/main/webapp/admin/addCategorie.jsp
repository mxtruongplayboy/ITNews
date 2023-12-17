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
        <title>Quản lý danh mục</title>
        <link rel="icon" href="../assets/logo/logo-small.png" type="image/png">
        <script>
	      function validateForm() {
              var name = document.getElementById("name").value;
              var slug = document.getElementById("slug").value;
              if(name == "" || slug == "") {
                  alert('Vui lòng nhập nội dung trước khi xác nhận.');
                  return false;
              }
              return true;
          }
	      
	      function cancel() {
	    	  window.location.href = '../category/getAll';
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
    	categorie Categorie = (categorie)request.getSession().getAttribute("Categorie");
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
         				<h2 class="myform__title">Thêm danh mục</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../category/create" class="myform-form" onsubmit="return validateForm()">      
					        <label>Tên danh mục</label>
					        <input type="text" id="name" name="name" class="input_text" value="<%= Categorie.getName() %>"/><br /><br />			           	     
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
					        <label>Trạng thái</label>
					        <select name="status" class="myform__status">
					            <option value="Hoạt động">Hoạt động</option>
					            <option value="Không hoạt động">Không hoạt động</option>
					        </select>
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
    </body>
</html>
