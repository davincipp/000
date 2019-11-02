<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:choose>
    <c:when test="${param.type eq 'reply' && not empty param.discussionId}">
      <title>回覆文章 | 論壇</title>
    </c:when>
    <c:when test="${param.type eq 'new'}">
      <title>發表文章 | 論壇</title>
    </c:when>
    <c:when test="${param.type eq 'update' &&  not empty param.discussionId}">
      <title>修改文章 | 論壇</title>
    </c:when>
</c:choose>

    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"> -->
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
		    $('#previewCoverImage').attr('src','${pageContext.request.contextPath}/forum/img?type=cover&id=${ud.id}').css('display','block');
		    var d = '${ud.deadline}'.split(' ');
	  	    var t = d[1].split('.');
		    $('#deadlineDate').val(d[0]);
		    $('#deadlineTime').val(t[0]);
		    $('#summernote').summernote('code', '<jsp:include page="/forum/text?type=discussion&id=${ud.id}" />');
		  </c:when>
		</c:choose>
		
	});
</script>
</head>

<body class="bg-light">

<!-- NAV -->
    <jsp:include page="navbar.jsp" />

<div class="container bg-white py-3 shadow">

	<div class="container">
<!-- ErrorMsg -->
		<div class="row text-danger text-center font-weight-bold">
		  <h3>
			${ErrorMsg.author}
			${ErrorMsg.title}
			${ErrorMsg.content}
			${ErrorMsg.deadline}
		  </h3>
		</div>

<!-- form -->
		<c:choose>
		
            <c:when test="${param.type eq 'reply' && not empty param.discussionId}">
              <form id="editor" method="post" enctype="multipart/form-data" action="reply.do">
              <input class="form-control d-none" type="text" name="type" value="${param.type}" >
              <input class="form-control d-none" type="text" name="discussionId" value="${param.discussionId}">
            </c:when>
            
            <c:when test="${param.type eq 'new'}">
              <form id="editor" method="post" enctype="multipart/form-data" action="submit.do">
              <input class="form-control d-none" type="text" name="type" value="${param.type}" >
		    </c:when>
		    
		    <c:when test="${param.type eq 'update' &&  not empty param.discussionId}">
              <form id="editor" method="post" enctype="multipart/form-data" action="submitUpdate.do">
              <input class="form-control d-none" type="text" name="type" value="${param.type}" >
              <input class="form-control d-none" type="text" name="discussionId" value="${param.discussionId}">
            </c:when>
		    
        </c:choose>
        
        <c:if test="${param.type eq 'new' || param.type eq 'update'}">
		  
		  <div class="form-group">
		    <label class="text-primary">標題</label>
		    <input id="title" class="form-control" type="text" name="title" value="${param.title}" required>
		  </div>
		  
		  <!-- 揪團、拍賣用 ↓ -->
		  <div class="form-group">
		    <label class="text-primary">文章封面圖</label>
		    <input class="form-control-file" type="file" name="coverImage" accept="image/gif,image/jpeg,image/png" onchange="previewImage(this)">
		  	<img id="previewCoverImage" src="" style="display: none; max-height: 150px;">
		  </div>
		  <!-- 揪團、拍賣用 ↑ -->
		  
		  <!-- 揪團用 ↓ -->
		  <div class="row">
			<div class="form-group col-md-6">
			  <label class="text-primary">揪團截止日期</label> 
			  <input id="deadlineDate" class="form-control" type="date" name="deadlineDate" value="${param.deadlineDate}" required>
			</div>
			<div class="form-group col-md-6">
			  <label class="text-primary">時間</label>
			  <input id="deadlineTime" class="form-control" type="time" name="deadlineTime" value="${param.deadlineTime}" required>
			</div>
		  </div>
		  <!-- 揪團用 ↑ -->
		  
        </c:if>
        
		  
		  <div class="form-group">
		    <label class="text-primary">文章內容</label>
		    <textarea id="summernote" name="content"></textarea>
		  </div>
		  
		  <button id="btSend" type="submit" class="btn btn-primary btn-lg btn-block">送出</button>
		</form>
		
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
</body>

</html>