<%@page import="model.Bean.post"%>
<%@page import="model.Bean.categorieFK"%>
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
	      function validateForm() {
              var title = document.getElementById("title").value;
              var slug = document.getElementById("slug").value;
              var image = document.getElementById("image").value;
              if(title == "" || slug == "" || image == "") {
                  alert('Vui lòng nhập nội dung trước khi xác nhận.');
                  return false;
              }
              return true;
          }
	      
	      function cancel() {
	    	  window.location.href = '../post/getAll';
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
         				<h2 class="myform__title">Thêm bài viết</h2>
         				<div class="myform__line"></div>
					    <form method="POST" action="../post/create" class="myform-form" onsubmit="return validateForm()" enctype="multipart/form-data">
					        <label>Tiêu đề</label>
					        <input type="text" id="title" name="title" class="input_text"/>		       
					        <label>Ảnh</label>
					        <input type="file" id="image" name="image" accept="image/*" class="input_file"/><br /><br />
					        <label>Nội dung</label><br /><br />
					        <textarea id="content" name="content" class="myform__editor"></textarea><br /><br />
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
							        <label>Danh mục</label>
							        <select name="categorieID" class="myform__status">
							        <%
								        List<categorieFK> listCategorieFK = (List<categorieFK>)request.getSession().getAttribute("listCategorieFK");
										if(listCategorieFK != null) {
											for(categorieFK CategorieFK : listCategorieFK) {
							        %>
							            <option value="<%= CategorieFK.getId() %>"><%= CategorieFK.getName() %></option>
							        <%
											}
										}
							        %>
							        </select>
						        </div>
						        <input name="accountsID" value="<%= ((account)(request.getSession().getAttribute("AccountLogin"))).getId() %>" hidden/>
					        </div>
					        <br /><br />
					        <label>Tin nổi bật</label>
					        <label>
							   <input type="radio" name="hot" value="true"> Tin nổi bật
							</label>
							<label>
							   <input type="radio" name="hot" value="false" checked> Không nổi bật
							</label>
							<div class="myform_footer footer_post">
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
        <script type="text/javascript" src="<%=request.getContextPath()%>/lib/ckeditor/ckeditor.js"></script>
        <script>
        	CKEDITOR.replace('content');
        </script>
    </body>
</html>
