<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.nav-item{
  letter-spacing: 2px;
  font-size: 20px;
}
</style>

   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Fititude</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">首頁 </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">專欄</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            社群
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<!--                             <table> -->
<!--                                 <tr> -->
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/grouping/activityList.jsp">揪團</a>
<!--                                 </tr> -->
<!--                                 <tr> -->
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/forum/discussionList.jsp">論壇</a>
<!--                                 </tr> -->
<!--                                 <tr> -->
                                <a class='dropdown-item' href='#'>拍賣</a>
<!--                                 </tr> -->
<!--                             </table> -->
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">商城</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">登入</a>
                    </li>

                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>

    </nav>
  

 
 
 
 
