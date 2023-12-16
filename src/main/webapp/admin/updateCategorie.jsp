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
        <title>Quản lý danh mục</title>
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
         				<h2 class="myform__title">Cập nhật thông tin danh mục</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../category/update" class="myform-form" onsubmit="return validateForm()">
					    	<%
						    	categorie Categorie = (categorie)request.getSession().getAttribute("Categorie");
								if(Categorie != null) {
									request.getSession().removeAttribute("Categorie");
					    	%>
					    	<label>Mã danh mục</label>
					        <input type="text" id="id" name="id" value="<%= Categorie.getId() %>" readonly class="input_text"/><br /><br />
					        <label>Tên danh mục</label>
					        <input type="text" id="name" name="name" value="<%= Categorie.getName() %>" class="input_text"/><br /><br />		     
					        <div class="myform_block">
						        <div class="myform_row">
						        	<label>Ngày tạo</label>
					        		<input type="date" id="created_at" name="created_at" value="<%= Categorie.getCreated_at() %>" class="input_date"/>
						        </div>
					        	<div class="myform_row">
					        		<label>Ngày cập nhật</label>
					        		<input type="date" id="updated_at" name="updated_at" value="<%= LocalDate.now() %>" readonly class="input_date"/>
					        	</div>
					        </div>
					        <br /><br />
					        <label>Trạng thái</label>
					        <select name="status" class="myform__status">
					        	<% if(Categorie.getStatus().equals("Hoạt động")) {%>
						            <option value="Hoạt động" selected>Hoạt động</option>
						            <option value="Không hoạt động">Không hoạt động</option>
						        <% } else {%>
						        	<option value="Hoạt động" >Hoạt động</option>
						            <option value="Không hoạt động" selected>Không hoạt động</option>
						        <% } %>
					        </select>
					        <br /><br />
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
