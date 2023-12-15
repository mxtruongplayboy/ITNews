<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Insert title here</title>
    </head>
    <body>
        <section class="sidebar" id="sidebar">
            <a href="#!" class="brand">
                <img
                    class="brand__logo"
                    src="../assets/images/avatar.jpg"
                    alt="Logo-ITFaculty"
                />
                <p class="brand__text">Quản lý tin tức</p>
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="" class="nav-link">
                        <i class="fa-solid fa-house nav-icon"></i>
                        Trang chủ
                    </a>
                </li>

                <li class="nav-item nav-item__active">
                    <a class="nav-link" href="../C_Categorie">
                        <i class="fa-solid fa-film nav-icon"></i>
                        Danh mục
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="../C_Post">
                        <i class="fa-solid fa-receipt nav-icon"></i>
                        Tin tức
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="../C_Account">
                        <i class="fa-solid fa-chart-bar nav-icon"></i>
                        Quản trị viên
                    </a>
                </li>
            </ul>
        </section>
    </body>
</html>
