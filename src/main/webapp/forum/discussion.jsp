<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${discussion.title} | 論壇</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body class="bg-light">
    <!-- NAV -->
    <jsp:include page="navbar.jsp" />

<div class="container bg-white py-3 shadow">

    <!-- 文章 -->
    <div class="main">
        <div class="container my-3">
            <div class="row">

                <div class="col-lg-9 col-sm-12 px-4">

                    <div class="row mb-3">
                        <div class="col-lg-12 col-sm-12">
                            <a class="text-secondary text-decoration-none" href="${pageContext.request.contextPath}/forum/">◀ 回列表</a>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12 col-sm-12 text-primary">
                        
                            <table class="w-100 h-100">
                              <tbody>
                                <tr>
                                  <td class="align-middle"><h3>${discussion.title}</h3></td>
                                </tr>
                              </tbody>
                            </table>
                        
                            
                        </div>
<!--                         <div class="col-lg-2 col-sm-12 text-right"> -->
<!--                             <table class="w-100 h-100"> -->
<!--                               <tbody> -->
<!--                                 <tr> -->
<!--                                   <td class="align-middle"><button type="button" class="btn btn-primary btn-sm">關注</button></td> -->
<!--                                 </tr> -->
<!--                               </tbody> -->
<!--                             </table> -->
<!--                         </div> -->
                    </div>
                    <hr class="bg-secondary">
                    <div class="row mb-3">
                        <div class="col-lg-12 col-sm-12 mr-auto">
                            <img src="${pageContext.request.contextPath}/forum/img?type=member&id=${discussion.author.pkey}"
                                style="height: 50px;">
                            <span>${discussion.author.name}</span>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-lg-12 col-sm-12">
                            <jsp:include page="/forum/text?type=discussion&id=${discussion.id}" />
                            <!-- ↓ -->
                            	
                            <fmt:formatDate value="${discussion.deadline}" pattern="yyyy-MM-dd HH:mm" /><br>
                            <!-- ↑ -->
                        </div>
                    </div>
                    <div class="row mb-5">
                        <div class="col-lg-12 col-sm-12">
                            <div class="btn-group btn-group-toggle" data-toggle="buttons">

                                    <button id="d${discussion.id}Minus" type="button" class="btn btn-light pointBtn"
                                        data-params="minus|${discussion.id}| ">－</button>

                                    <div id="d${discussion.id}" class="btn">0</div>

                                    <button id="d${discussion.id}Plus" type="button" class="btn btn-light pointBtn"
                                        data-params="plus|${discussion.id}| ">＋</button>
                                        
                            </div>
                        </div>
                    </div>

                    <!-- 回答 -->

                    <!-- ↓↓↓↓↓ -->
                <c:forEach var="r" items="${replies}" varStatus="v">
                    <div class="card mb-1">
                        <div class="card-body">
                            <div class="row mb-3">
                            <div class="col-lg-12 col-sm-12">
                            
                            <table class="w-100 h-100">
                              <tbody>
                                <tr>
                                  <td class="align-middle text-left">
                                    <img src="${pageContext.request.contextPath}/forum/img?type=member&id=${r.author.pkey}"
                                        style="height: 50px;">
                                    <span>${r.author.name}</span>
                                  </td>
                                  <td class="align-middle text-right">
                                    <span>#${v.count}</span><br>
                                    <span><fmt:formatDate value="${r.postTime}" pattern="yyyy-MM-dd HH:mm" /></span>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                            
                            </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-lg-12 col-sm-12">
                                  <%-- ${r.discussion.id} --%>
                                    <jsp:include page="/forum/text?type=reply&id=${r.id}" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 col-sm-12">
                                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
     
                                    <button id="r${r.id}Minus" type="button" class="btn btn-light pointBtn"
                                        data-params="minus|${discussion.id}|${r.id}">－</button>

                                    <div id="r${r.id}" class="btn">0</div>

                                    <button id="r${r.id}Plus" type="button" class="btn btn-light pointBtn"
                                        data-params="plus|${discussion.id}|${r.id}">＋</button>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                    <!-- ↑↑↑↑↑ -->
                    
                    
                    

                    <div class="row py-3">
                        <div class="col">
                            <a class="btn btn-primary btn-lg btn-block" href="<c:url value='editor.jsp?type=reply&discussionId=${discussion.id}' />" role="button">回答</a>
                        </div>
                    </div>
                    <!-- 非發文者顯示 ↑ -->


                </div>
                <div class="col-lg-3 col-sm-12 px-4">
                    <div class="row">
                        <div class="col-lg-12 col-sm-12 ml-auto text-right">
                            <div class="card text-center">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">發布時間<br>
                                        <fmt:formatDate value="${discussion.postTime}" pattern="yyyy-MM-dd HH:mm"/>
                                    </li>
                                    <li class="list-group-item">回答數量<br>
                                        ${fn:length(replies)}</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- 發文者顯示 ↓ -->
                    <c:if test="${sessionScope.LoginOK.pkey == discussion.author.pkey}">
                      <div class="row my-3">
                          <div class="col-lg-12 col-sm-12 ml-auto text-center">
                              <a class="btn btn-warning" href="<c:url value="update.do?d=${discussion.id}" />" role="button">修改</a>
                              <a class="btn btn-danger" href="<c:url value="delete.do?d=${discussion.id}" />" role="button">刪除</a>
                          </div>
                      </div>
                    </c:if>
                    <!-- 發文者顯示 ↑ -->
                </div>

            </div>
        </div>
    </div>

</div>
<script>
$('.pointBtn').click(function () {
    var params = $(this).data('params').split('|');
    var data = new FormData();
    data.append("type", params[0]);
    data.append("did", params[1]);
    data.append("rid", params[2]);

    $.ajax({
        url: 'point.do',
        type: 'POST',
        contentType: false,
        cache: false,
        processData: false,
        data: data,
        dataType: 'JSON',
        success: function (j) {
            console.log(j);
            var pointId = '#' + j.id;
            var pointVal = j.point;
            var pointType = j.type;
            $(pointId).html(pointVal);
            
            var btnMinusId = '#' + j.id + 'Minus';
            var btnPlusId = '#' + j.id + 'Plus';
            $(btnMinusId).removeClass(' active');
            $(btnPlusId).removeClass(' active');
            if (pointType == 1) {
            	$(btnPlusId).addClass(' active');
            } else if (pointType == -1) {
            	$(btnMinusId).addClass(' active');
            }
        },
        error: function(){
        	alert('ERROR');
        },

    });

});
</script>
</body>

</html>