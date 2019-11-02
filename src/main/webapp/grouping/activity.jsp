<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${activity.title}|揪團</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css">

</head>

<body>
	<!-- -----------------------------Navbar-------------------------------- -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#">Fititude</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="#">首頁 </a></li>
					<li class="nav-item"><a class="nav-link" href="#">專欄</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> 社群 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="active dropdown-item" href="#">揪團<span
								class="sr-only">(current)</span></a> <a class="dropdown-item"
								href="#">論壇</a> <a class="dropdown-item" href="#">拍賣</a>

						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">商城</a></li>
				</ul>
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="#">登入</a></li>

				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</div>

	</nav>
	<!-- -----------------------------Sidebar-------------------------------- -->
	<jsp:include page="sidebar.jsp" />
	<!----------------------------------Main-------------------------------- -->
	<div class="row ml-3 my-3 ">
		<div class="col-lg-12 col-sm-12">
			<a class="text-secondary text-decoration-none"
				href="${pageContext.request.contextPath}/grouping/">◀ 回列表</a>
		</div>
	</div>
	<div class="container py-3 my-2 shadow">
		<!-- 文章 -->
		<div class="aactivitymain">
			<div class="container w-100">
				<!-- 發文者顯示-->
				<c:if test="${sessionScope.LoginOK.pkey == activity.author.pkey}">
					<div class="row ml-0">
						<div class="col-lg-12 col-sm-12 p-2 text-left">
							<a class="btn btn-warning"
								href="<c:url value='update.do?d=${activity.id}' />"
								role="button">修改</a> <a class="btn btn-danger"
								href="<c:url value='delete.do?d=${activity.id}' />"
								role="button">刪除</a>
						</div>
					</div>
				</c:if>
				<!-- 發文者顯示 ↑ -->
			</div>
			<div class="container text-center 單一活動訊息">
				<div class="row">
					<div class="col-xl-6 col-lg-6 col-10 card itemInfo"
						style="background-color: #E0E0E0;">
						<div class="row">
							<div class="col-12">
								<ul class="text-left" style="list-style-type: none">
									<li class="pt-2 text-center">
										<h4>活動資訊</h4>
									</li>
									<hr class="mt-0">
									<li>
										<h5>
											<span><img src="./../images/condition.svg" alt="">
											</span>&nbsp;<span>活動名稱：${activity.title}</span>
										</h5>
									</li>
									<li>
										<h5>
											<span><img src="./../images/price.svg" alt=""> </span>&nbsp;<span>活動所需費用：<fmt:formatNumber
													value="${activity.price}" pattern="#######" />
											</span>
										</h5>
									</li>
									<li>
										<h5>
											<span><img src="./../images/location.svg" alt="">
											</span>&nbsp;<span>地點：${activity.location}</span>
										</h5>
									</li>
									<li>
										<h5>
											<span><img src="./../images/description.svg" alt="">
											</span>&nbsp;<span>活動內容：</span><br>
										</h5> <%--                             <img src="${pageContext.request.contextPath}/grouping/img?type=cover&id=${activity.author.pkey}" --%>
										<!--                                 style="width:100px;">              -->

										<jsp:include
											page="/grouping/text?type=activity&id=${activity.id}" /> <!-- ↓ -->

										<!-- ↑ -->
									</li>
									<li class="border-bottom">
										<h5>
											<span><img src="./../images/time-stamp.svg" alt="">
											</span>&nbsp; <span>活動截止時間：</span>
											<fmt:formatDate value="${activity.deadline}"
												pattern="yyyy-MM-dd HH:mm" />
										</h5>
									</li>
								</ul>
							</div>
						</div>
						<p>
							<button type="button" class="btn btn-info">我要參加</button>
						</p>
					</div>
					<!-------------------------------- 右:活動封面照&發文時間&分享bt ------------------------------------------->
					<div class="col-xl-5 col-lg-5 col-10 ">
						<!--       發文時間     -->
						<div class="row">
							<!-- 賣家資訊-->
							<div class="seller col-10 text-left ">
								<div class="Z-l">
									<div>
										<img class="rounded-circle" src="./../images/profile.png"
											alt="iqiqiiq0822"> &nbsp; <span class="text-light">活動發起人：${activity.author.name}</span>&nbsp;&nbsp;
										<span class="text-light">會員積分：</span>
									</div>
								</div>
							</div>
							<!-- 發布時間 -->
							<div class="col-lg-12 col-sm-12 ml-auto text-right">
								<div class="card text-center">
									<ul class="list-group list-group-flush">
										<li class="list-group-item" style="background-color: #E0E0E0;">發布時間<br>
											<fmt:formatDate value="${activity.postTime}"
												pattern="yyyy-MM-dd HH:mm" />
										</li>
										<li class="list-group-item" style="background-color: #E0E0E0;">回答數量<br>
											${fn:length(activityReplies)}
										</li>
									</ul>
								</div>
							</div>
							<div class="col-lg-12 col-sm-12 ml-auto card marketSlider"
								style="background-color: #E0E0E0;">
								<div id="carouselExampleControls" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<%--                             <c:forEach var="d" items="${activity}"> --%>
										<div class="carousel-item active">
											<img
												src="${pageContext.request.contextPath}/grouping/img?type=cover&id=${activity.id}"
												class="d-block w-100 img-thumbnail">
											<%--        								<img src="${pageContext.request.contextPath}/grouping/text?type=activity&id=${activity.id}" class="d-block w-100 img-thumbnail" alt="..."> --%>
											<%--                                 	<jsp:include page="/grouping/img?type=member&id=${d.author.pkey}" /> --%>
										</div>
										<%--                              </c:forEach> --%>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleControls" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleControls" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
						</div>

						<!--     分享bt    -->
						<!-- Sharingbutton Facebook -->
						<!-- 臉書連結 -->
						<script>
							var fbhtml_url = window.location.toString();
						</script>
						<div class="col-lg-12 col-sm-12 text-left">
							<a class="resp-sharing-button__link" href="javascript:void(0);"
								onclick="window.open('http://www.facebook.com/sharer/sharer.php?u='+fbhtml_url);return false;"
								target="_blank" rel="noopener" aria-label="Facebook"> <!-- <a class="resp-sharing-button__link" href="http://www.facebook.com/sharer/sharer.php?u='+fbhtml_url" target="_blank" rel="noopener" aria-label="Facebook"> -->
								<div
									class="resp-sharing-button resp-sharing-button--facebook resp-sharing-button--medium">
									<div aria-hidden="true"
										class="resp-sharing-button__icon resp-sharing-button__icon--solid">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                        <path
												d="M18.77 7.46H14.5v-1.9c0-.9.6-1.1 1-1.1h3V.5h-4.33C10.24.5 9.5 3.44 9.5 5.32v2.15h-3v4h3v12h5v-12h3.85l.42-4z" />
                                    </svg>
									</div>
									Facebook
								</div>
							</a>

							<!-- Sharingbutton Twitter -->
							<a class="resp-sharing-button__link"
								href="https://twitter.com/intent/tweet/?text=Super%20fast%20and%20easy%20Social%20Media%20Sharing%20Buttons.%20No%20JavaScript.%20No%20tracking.&amp;url=http%3A%2F%2Fsharingbuttons.io"
								target="_blank" rel="noopener" aria-label="Twitter">
								<div
									class="resp-sharing-button resp-sharing-button--twitter resp-sharing-button--medium">
									<div aria-hidden="true"
										class="resp-sharing-button__icon resp-sharing-button__icon--solid">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                        <path
												d="M23.44 4.83c-.8.37-1.5.38-2.22.02.93-.56.98-.96 1.32-2.02-.88.52-1.86.9-2.9 1.1-.82-.88-2-1.43-3.3-1.43-2.5 0-4.55 2.04-4.55 4.54 0 .36.03.7.1 1.04-3.77-.2-7.12-2-9.36-4.75-.4.67-.6 1.45-.6 2.3 0 1.56.8 2.95 2 3.77-.74-.03-1.44-.23-2.05-.57v.06c0 2.2 1.56 4.03 3.64 4.44-.67.2-1.37.2-2.06.08.58 1.8 2.26 3.12 4.25 3.16C5.78 18.1 3.37 18.74 1 18.46c2 1.3 4.4 2.04 6.97 2.04 8.35 0 12.92-6.92 12.92-12.93 0-.2 0-.4-.02-.6.9-.63 1.96-1.22 2.56-2.14z" />
                                    </svg>
									</div>
									Twitter
								</div>
							</a>

							<!-- Sharingbutton Tumblr -->
							<a class="resp-sharing-button__link"
								href="https://www.tumblr.com/widgets/share/tool?posttype=link&amp;title=Super%20fast%20and%20easy%20Social%20Media%20Sharing%20Buttons.%20No%20JavaScript.%20No%20tracking.&amp;caption=Super%20fast%20and%20easy%20Social%20Media%20Sharing%20Buttons.%20No%20JavaScript.%20No%20tracking.&amp;content=http%3A%2F%2Fsharingbuttons.io&amp;canonicalUrl=http%3A%2F%2Fsharingbuttons.io&amp;shareSource=tumblr_share_button"
								target="_blank" rel="noopener" aria-label="Tumblr">
								<div
									class="resp-sharing-button resp-sharing-button--tumblr resp-sharing-button--medium">
									<div aria-hidden="true"
										class="resp-sharing-button__icon resp-sharing-button__icon--solid">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                        <path
												d="M13.5.5v5h5v4h-5V15c0 5 3.5 4.4 6 2.8v4.4c-6.7 3.2-12 0-12-4.2V9.5h-3V6.7c1-.3 2.2-.7 3-1.3.5-.5 1-1.2 1.4-2 .3-.7.6-1.7.7-3h3.8z" />
                                    </svg>
									</div>
									Tumblr
								</div>
							</a>

							<!-- Sharingbutton E-Mail -->
							<a class="resp-sharing-button__link"
								href="mailto:?subject=Super%20fast%20and%20easy%20Social%20Media%20Sharing%20Buttons.%20No%20JavaScript.%20No%20tracking.&amp;body=http%3A%2F%2Fsharingbuttons.io"
								target="_self" rel="noopener" aria-label="E-Mail">
								<div
									class="resp-sharing-button resp-sharing-button--email resp-sharing-button--medium">
									<div aria-hidden="true"
										class="resp-sharing-button__icon resp-sharing-button__icon--solid">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                        <path
												d="M22 4H2C.9 4 0 4.9 0 6v12c0 1.1.9 2 2 2h20c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zM7.25 14.43l-3.5 2c-.08.05-.17.07-.25.07-.17 0-.34-.1-.43-.25-.14-.24-.06-.55.18-.68l3.5-2c.24-.14.55-.06.68.18.14.24.06.55-.18.68zm4.75.07c-.1 0-.2-.03-.27-.08l-8.5-5.5c-.23-.15-.3-.46-.15-.7.15-.22.46-.3.7-.14L12 13.4l8.23-5.32c.23-.15.54-.08.7.15.14.23.07.54-.16.7l-8.5 5.5c-.08.04-.17.07-.27.07zm8.93 1.75c-.1.16-.26.25-.43.25-.08 0-.17-.02-.25-.07l-3.5-2c-.24-.13-.32-.44-.18-.68s.44-.32.68-.18l3.5 2c.24.13.32.44.18.68z" />
                                    </svg>
									</div>
									E-Mail
								</div>
							</a>
						</div>
					</div>
				</div>
				<!------------------ 下方留言及賣家訊息 ---------------------->
				<div class="row border-bottom">
					<div class="mesage col-xl-10 col-10 card"
						style="background-color: #E0E0E0;">
						<!-- ↓↓↓↓↓ -->
						<c:forEach var="r" items="${activityReplies}" varStatus="v">
							<div class="row mb-3">
								<div class="col-lg-12 col-sm-12">

									<table class="w-100 h-100">
<!-- 										style="border-bottom: 1px solid red;"> -->
										<tbody>
											<tr class="align-left">
												<td align="left"><span>作者：${r.author.name}</span></td>
												<td align="right"><span class="ml-0">#${v.count}</span></td>
											</tr>
											<tr class="align-middle text-left">
												<td>
													<div class="col-lg-12 col-sm-12">
														<%-- ${r.activity.id} --%>
														<jsp:include page="/grouping/text?type=reply&id=${r.id}" />
													</div>
												</td>
											</tr>
											<tr class="align-middle text-right ">
												<td><span>回應時間：<fmt:formatDate
															value="${r.postTime}" pattern="yyyy-MM-dd HH:mm" /></span></td>
											</tr>
										</tbody>
									</table>
									<hr>
								</div>
							</div>
						</c:forEach>
						<!-- ↑↑↑↑↑ -->
						<div>
							<a class="btn btn-info btn-sm btn-block"
								href="
                                <c:url value='editor.jsp?type=reply&activityId=${activity.id}' />"
								role="button">留言 </a>
						</div>

					</div>
					<!-- 留言串-->
				</div>

			</div>
		</div>
	</div>








	<!-- ----------------------------coryright------------------------------- -->
	<div class="copyright bg-dark text-light text-center p-3">
		Copyright © 2019 Fititude. All rights reserved.</div>







	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
</body>

</html>