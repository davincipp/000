<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>發布文章 | 社群</title>

    <c:choose>
        <c:when test="${param.type eq 'reply' && not empty param.activityId}">
            <title>回覆文章 | 揪團</title>
        </c:when>
        <c:when test="${param.type eq 'new'}">
            <title>發表文章 | 揪團</title>
        </c:when>
        <c:when test="${param.type eq 'update' &&  not empty param.activityId}">
            <title>修改文章 | 揪團</title>
        </c:when>
    </c:choose>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script> -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
    <script src="js/summernote-zh-TW.js"></script>

    <script>
	$(document).ready(function() {
		<c:choose>
		  <c:when test="${param.type eq 'new'}">
		    $('#summernote').summernote('code', '${param.content}');
		  </c:when>
		  <c:when test="${param.type eq 'update'}">
		    $('#title').val('${ud.title}');
		    $('#previewCoverImage').attr('src','${pageContext.request.contextPath}/grouping/img?type=cover&id=${ud.id}').css('display','block');
		    var d = '${ud.deadline}'.split(' ');
	  	    var t = d[1].split('.');
		    $('#deadlineDate').val(d[0]);
		    $('#deadlineTime').val(t[0]);
		    $('#summernote').summernote('code', '<jsp:include page="/grouping/text?type=activity&id=${ud.id}" />');
		  </c:when>
		</c:choose>
		
	});
    </script>

    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
    <!-- -----------------------------Navbar-------------------------------- -->
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
                        <a class="nav-link" href="#">專欄</a><span class="sr-only">(current)</span>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            社群
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">揪團</a>
                            <a class="dropdown-item" href="#">論壇</a>
                            <a class="dropdown-item" href="#">拍賣</a>

                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">商城</a>
                    </li>
                </ul>
                <!-- <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">登入</a>
                    </li>

                </ul> -->
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>

    </nav>
    <!----------------------------------------- main --------------------------------------->
    <div class=" container shadow py-3">

        <div class="postmain container" style="background-color: #FCF4D7;">
            <!-- ErrorMsg -->
             <div class="row text-danger text-center font-weight-bold">
                <h5>
                    ${ErrorMsg.author}<br>
                    ${ErrorMsg.title}<br>
                    ${ErrorMsg.content}<br>
                    ${ErrorMsg.deadline}<br>
                    ${ErrorMsg.location}<br>
                </h5>
            </div> 

            <!-- form -->
            <c:choose>

                <c:when test="${param.type eq 'reply' && not empty param.activityId}">
                    <form id="editor" method="post" enctype="multipart/form-data" action="reply.do">
                        <input class="grouping-control d-none" type="text" name="type" value="${param.type}">
                        <input class="grouping-control d-none" type="text" name="activityId"
                            value="${param.activityId}">
                </c:when>

                <c:when test="${param.type eq 'new'}">
                    <form id="editor" method="post" enctype="multipart/form-data" action="submit.do">
                        <input class="grouping-control d-none" type="text" name="type" value="${param.type}">
                </c:when>

                <c:when test="${param.type eq 'update' &&  not empty param.activityId}">
                    <form id="editor" method="post" enctype="multipart/form-data" action="submitUpdate.do">
                        <input class="grouping-control d-none" type="text" name="type" value="${param.type}">
                        <input class="grouping-control d-none" type="text" name="activityId"
                            value="${param.activityId}">
                </c:when>

            </c:choose>

            <c:if test="${param.type eq 'new' || param.type eq 'update'}">

                <div class="form-group pt-3 ">
                    <label class="text-secondary font-weight-bold">標題</label>
                    <input id="title" class="form-control" type="text" name="title" value="${param.title}" required>
                </div>

                <!-- 揪團、拍賣用 ↓ -->
                <div class="form-group">
                    <label class="text-secondary font-weight-bold">文章封面圖</label>
                    <input class="form-control-file" type="file" name="coverImage" multiple="multiple"
                        accept="image/gif,image/jpeg,image/png" onchange="previewImage(this)">
                    <img id="previewCoverImage" src="" style="display: none; max-height: 150px;">
                </div>
                <!-- 揪團、拍賣用 ↑ -->

                <!-- 揪團用 ↓ -->
                <div class="row">
                    <div class="grouping-group col-md-3">
                        <label class="text-secondary font-weight-bold">揪團截止日期</label>
                        <input id="deadlineDate" class="grouping-control" type="date" name="deadlineDate"
                            value="${param.deadlineDate}" required>
                    </div>
                    <div class="grouping-group col-md-3">
                        <label class="text-secondary font-weight-bold">時間</label><br>
                        <input id="deadlineTime" class="grouping-control" type="time" name="deadlineTime"
                            value="${param.deadlineTime}" required>
                    </div>
                    <div class="grouping-group col-md-3">
                        <label class="text-secondary font-weight-bold">費用 | 價格</label><br>
                        <input id="price" class="grouping-control" type="number" name="price" 
                        	value="${param.price}" min="1">
                    </div>
                    <div class="grouping-group col-md-3">
                        <label class="text-secondary font-weight-bold">地點</label><br>
                        <input id="location" class="grouping-control" type="text" name="location" 
                        	value="${param.location}">
                    </div>
                </div>
                <!-- 揪團用 ↑ -->

            </c:if>

<!--             <div class="keyword"> -->
<!--                 <label class="text-secondary font-weight-bold">文章關鍵字： -->
<!--                     <form　action="" class="text-secondary"><br> -->
<!--                         <input type="checkbox" name="keyword1" value=""> 減脂 -->
<!--                         <input type="checkbox" name="keyword2" value=""> 增加肌肉 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 運動時間 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 二頭肌 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 上半身 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 背肌 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 下半身 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 人魚線 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 高蛋白 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 飲食控制 -->
<!--                         <input type="checkbox" name="keyword3" value=""> 有氧運動 &nbsp;&nbsp; -->
<!--                         <input type="submit" value="確認" class=" text-left"> -->
<!--                     </form> -->
<!--                 </label><br> -->
<!--             </div> -->
            <div class="form-group">
		    <label class="text-primary">文章內容</label>
		    <textarea id="summernote" name="content"></textarea>
		  </div>

            <div class="pb-3 text-right">
                <button id="btSend" type="submit" class="btn btn-lg text-light"
                    style="background-color: #1A4D7F;">送出</button>
                </form>
            </div>
        </div>


        <!-- Modal  -->
        <div class="modal fade" id="my-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">請填寫文章內容</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">確定</button>
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