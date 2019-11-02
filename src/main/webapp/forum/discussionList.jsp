<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>論壇 | Fititude</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body class="bg-light">
    <!-- NAV -->
    <jsp:include page="navbar.jsp" />

<div class="container bg-white py-3 shadow">
    <!-- top -->
    <div class="top my-3">
        <div class="container">
            <div class="row">
                <div class="col-6 mx-3">
                    <h1>論壇</h1>
                </div>
                <div class="col-4 ml-auto pr-5 pt-2 text-right">
                    <a class="btn btn-primary btn-lg" href="<c:url value="editor.jsp?type=new" />" role="button">發文</a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="?sort=new">新到舊</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="?sort=old">舊到新</a>
                        </li>
<!--                         <li class="nav-item"> -->
<!--                             <a class="nav-link" href="?sort=useful">實用分數高到低</a> -->
<!--                         </li> -->
<!--                         <li class="nav-item"> -->
<!--                             <form class="form-inline ml-4 my-lg-0"> -->
<!--                                 <input class="form-control mr-sm-2" type="search" placeholder="Search" -->
<!--                                     aria-label="Search"> -->
<!--                             </form> -->
<!--                         </li> -->
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- main -->
    <div class="main">
        <div class="container pb-3">
        <hr class="bg-secondary">
            <!-- ||| -->
            <c:forEach var="d" items="${discussions}" varStatus="v">
            <div class="reply" class="row">
                <div class="col-lg-12 col-sm-12">
                    <div class="card border-0">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-8 col-sm-12">
                                
                                <table class="w-100 h-100">
                                      <tbody>
                                        <tr>
                                          <td class="align-middle text-left">
                                            <!-- 揪團、拍賣圖片測試用 ↓ -->
                                                                                        測試用
                                            <img src="img?type=cover&id=${d.id}" height="50">
                                            <!-- 揪團、拍賣圖片測試用 ↑ -->
                                          </td>
                                          <td class="align-middle text-left">
                                            <h4>
                                              <a href="<c:url value='discussion?d=${d.id}' />" class="text-decoration-none">${d.title}</a>
                                            </h4>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                    
                                </div>
                                
                                <div class="col-lg-4 col-sm-12 ml-auto">
                                
                                    <table class="w-100 h-100">
                                      <tbody>
                                        <tr>
                                          <td class="align-middle text-left"><span><fmt:formatDate value="${d.postTime}" pattern="yyyy-MM-dd" /></span></td>
                                          <td class="align-middle text-right"><span>${d.author.name}</span> <img src="${pageContext.request.contextPath}/forum/img?type=member&id=${d.author.pkey}" style="height: 50px;"></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                    
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
            <hr class="bg-secondary">
            </c:forEach>
            <!-- ||| -->

        </div>
    </div>

    <!-- 分頁 -->
    <div class="pageNum">
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                        
                            <c:if test="${pageInfo.now - 1 > 0}">
                                <li class="page-item">
                                    <a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now - 1}' />" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${pageInfo.now > 2}">
                                <li class="page-item"><a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now - 2}' />">${pageInfo.now - 2}</a></li>
                            </c:if>
                            
                            <c:if test="${pageInfo.now > 1}">
                                <li class="page-item"><a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now - 1}' />">${pageInfo.now - 1}</a></li>
                            </c:if>
                            
                            <li class="page-item active"><a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now}' />">${pageInfo.now}</a></li>
                            
                            <c:if test="${pageInfo.now + 1 <= pageInfo.total}">
                                <li class="page-item"><a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now + 1}' />">${pageInfo.now + 1}</a></li>
                            </c:if>
                            
                            <c:if test="${pageInfo.now + 2 <= pageInfo.total}">
                                <li class="page-item"><a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now + 2}' />">${pageInfo.now + 2}</a></li>
                            </c:if>
                            
                            <c:if test="${pageInfo.now + 1 <= pageInfo.total}">
                                <li class="page-item">
                                    <a class="page-link" href="<c:url value='?sort=${sort}&page=${pageInfo.now + 1}' />" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>