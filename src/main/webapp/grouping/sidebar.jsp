<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.slidebar {
  /* border: 1px solid red; */
  width: 30px;
  position: fixed;
  z-index: 1;
  margin-left: 96%;
}

.letterstrait{
  font-size: 18px;
    font-weight: 500;
    line-height: 1.3;
    margin: 2px 3px
}

.letterstrait a{
  color: #ff0000;
  text-decoration:none
}
</style>

  
   <!-- -----------------------------Sidebar-------------------------------- -->
    <div class="slidebar">
    
        <div class="container py-5 ">
            <div class="row">
                <div class="letterstrait">
                    <div class="lsc-1" >    
                           <a href="${pageContext.request.contextPath}/grouping/">
                    <div class="activity">揪 </div>
                    <div class="activity">團</div>
                </a>
                </div>
                    <br>
                    <div class="lsc-2">
                            <a href="${pageContext.request.contextPath}/forum/">
                    <div class="forum">論</div>
                    <div class="forum">壇</div>
                </a>
                </div>
                    <br>
                    <div class="lsc-3">
                             <a href="${pageContext.request.contextPath}/market/">
                    <div class="market">拍</div>
                    <div class="market">賣</div>
                </a>
                </div>
                </div>
            </div>
        </div>
    </div>

 
 
 
