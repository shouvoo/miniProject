<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	#cl-dashboard{display: none;}
	.logdiv
	{
		position: absolute;
		display: inline-block;
		float: right;
		right: 50px;
		top:15px;
	}
	
	.logdiv li a
	{
		color: red;
		text-decoration: none;
	}
	
	.logdiv li
	{
		list-style: none;
	}
	
	.topCustom
	{	
		border: none;
		background: #0e0e0e9c;		
	};
	
	
	

</style>




<nav class="navbar navbar-inverse navbar-default navbar-fixed-top topCustom">		
	<div class="logdiv">
		<c:choose>
			<c:when test="${empty user}">
				 <li class="log"><a href="${pageContext.request.contextPath}/log/loginform">Login</a></li>
			</c:when>
			<c:otherwise>
				 <li class="log"><a href="${pageContext.request.contextPath}/log/logoutform">LogOut</a></li>
			</c:otherwise>
		</c:choose>
	</div>

	
	<div class="container-fluid">
	
		<a class="navbar-brand" href="${pageContext.request.contextPath}/main/index">
			<img src="${pageContext.request.contextPath}/img/logo/logo2.png" width="30" height="30" alt="">
		</a>
	
	 	<ul class="nav navbar-nav">
	   		<li><a href="${pageContext.request.contextPath}/about/about">ABOUT US</a></li>
			<li><a href="${pageContext.request.contextPath}/fixboard/fixboard">FIX</a></li>
			<li><a href="${pageContext.request.contextPath}/freeboard/freeboard">FREE BOARD</a></li>
			<li><a href="${pageContext.request.contextPath}/qna/qnaboard">QnA</a></li>
	    </ul>
  	</div>
  	
  	
</nav>





















