<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>揪團 | Fititude</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./css/style.css">

</head>

<body>
    <!-- -----------------------------Navbar-------------------------------- -->
    <jsp:include page="nav.jsp" />
    <!-- -----------------------------Sidebar-------------------------------- -->
    <jsp:include page="sidebar.jsp" />
    <!-- --------------------------------MainTop-------------------------------- -->
    <div class="activityMain py-2">
        <div class="col-xl-5 col-md-6 col-8 mx-auto">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <c:forEach var="d" items="${activitys}">
                        <div class="carousel-item active">
                            <img src="${pageContext.request.contextPath}/grouping/img?type=cover&id=${d.id}"
                                class="d-block w-100 img-thumbnail">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>活動名稱： ${d.title} </h5>
                                <p>截止時間 ${d.deadline}
                                    <fmt:formatDate value="${d.deadline}" pattern="yyyy-MM-dd" />
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
    <!-- ------------------------------Post BT------------------------------ -->
    <div class="activityMain">
        <div class="container p-0">
            <div class="row ">
                <div class="col border-bottom mb-0">
                    <div class="nav">
                        <div class="nav-item">
                            <a class="btn btn-sm btn-warning nav-link active" href="?sort=new">新到舊</a>
                        </div>
                        <div class="nav-item">
                            <a class="btn  btn-sm btn-warning nav-link" href="?sort=old">舊到新</a>
                        </div>
                    </div>
                </div>
                <div class="mr-4 text-right ">
                    <div class="nav-item mx-auto">
                        <a class="btn btn-warning nav-link font-weight-bold" href="<c:url value="
                            editor.jsp?type=new" />" >我來揪團</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- ------------------------------break--------------------------------- -->
    <!-- ---------------------------Activity List------------------------------ -->
    <div class="activityList">
        <div class="container py-3 my-2 shadow ">
            <div class="row">
                <c:forEach var="d" items="${activitys}" varStatus="v">
                    <div class="col-lg-4 col-sm-6 col-12">
                        <div id="cardActivity" class=" ">
                            <img src="${pageContext.request.contextPath}/grouping/img?type=cover&id=${d.id}"
                                class="d-block w-100 img-thumbnail">
                            <!--                              style="width:100px"　class="card-img-top"> -->
                            <div class="card-body">
                                <h5 class="card-title">${d.title}</h5>
                                <p class="card-text"> 地點：${d.location} <br>時間：
<%--                                     <fmt:formatNumber value="${d.price}" pattern="#######" /> --%>
                                    <fmt:formatDate value="${d.deadline}" pattern="yyyy-MM-dd" />
                                
                                </p>
                                <a href="<c:url value='activity?d=${d.id}' />" class="btn btn-primary">更多訊息</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- ---------------------------pagination------------------------------ -->
        <div class="pageNum">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="pages mt-5">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${pageInfo.now - 1 > 0}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now - 1}' />"
                                                aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pageInfo.now > 2}">
                                        <li class="page-item"><a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now - 2}' />">${pageInfo.now
                                                - 2}</a></li>
                                    </c:if>

                                    <c:if test="${pageInfo.now > 1}">
                                        <li class="page-item"><a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now - 1}' />">${pageInfo.now
                                                - 1}</a></li>
                                    </c:if>

                                    <li class="page-item active"><a class="page-link"
                                            href="<c:url value='?sort=${sort}&page=${pageInfo.now}' />">${pageInfo.now}</a>
                                    </li>

                                    <c:if test="${pageInfo.now + 1 <= pageInfo.total}">
                                        <li class="page-item"><a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now + 1}' />">${pageInfo.now
                                                + 1}</a></li>
                                    </c:if>

                                    <c:if test="${pageInfo.now + 2 <= pageInfo.total}">
                                        <li class="page-item"><a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now + 2}' />">${pageInfo.now
                                                + 2}</a></li>
                                    </c:if>

                                    <c:if test="${pageInfo.now + 1 <= pageInfo.total}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="<c:url value='?sort=${sort}&page=${pageInfo.now + 1}' />"
                                                aria-label="Next">
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
	</div>

        <!-- ----------------------------coryright------------------------------- -->
        <div class="copyright bg-dark text-light text-center p-3">
            Copyright © 2019 Fititude. All rights reserved.
        </div>







</body>

</html>