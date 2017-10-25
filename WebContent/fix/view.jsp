<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
.TopBar{font: 20px bold; color: #0085D5; background: rgb(239, 239, 239); width: 100%; position: fixed;
height: 27px; margin-top: 50px;}
.boardLook{float: right;}
.boardLike{float: right; margin-top: -7px;}
.LowBar{font: 20px bold; color: #0085D5; background: rgb(136, 136, 136); margin-bottom: 50px;
width: 100%; height: 28px; text-align: right;}
.LowBar *{border: 1px solid black; font-weight: bold; color: red;}
.boardCate{font-size: 13px;}
.SecondBar{color: black; width: 100%; margin-bottom: 10px; border: 1px solid black;
position: fixed; margin-top: 77px; background: white;}
.boardRegDate{float: right;}
.detail, .detailfir, .detaillas, .detailonl{height: auto; width: 500px;}
.detailfir{padding-top: 70px;}
.detaillas{padding-bottom: 50px;}
.detailonl{padding-top: 70px; padding-bottom: 50px;}
.board{overflow: auto; border: 2px thin black; padding-top: 60px;}
</style>
<div class="header">
		<c:import url="/include/topMenu.jsp" />
</div>
<div class="content">
	<div class="TopBar">
		<span class="boardCate">
		<a href="${pageContext.request.contextPath }/fix/search?cate=${fix.cate }&brand=전체&title=">${fix.cate }</a>
		>
		<a href="${pageContext.request.contextPath }/fix/search?cate=${fix.cate }&brand=${fix.brand }&title=">${fix.brand }</a>
		</span>
		<span class="boardTitle">${fix.title }</span>
		<span class="boardLook">조회수: ${fix.look }</span>
		<object class="boardLike" data="${pageContext.request.contextPath }/fix/likeform?no=${param.no}" width=150 height=35></object>
	</div>
	
	<div class="secondBar">
		<span class="boardWriter">작성자: ${fix.writer }</span>
		<span class="boardRegDate">작성일: <fmt:formatDate value="${fix.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></span>
	</div>
	
	<center>
	<c:forEach items="${file }" var="a" varStatus="v">
	
		<c:choose>
		<c:when test="${v.last && v.first}"><div class="detailonl"></c:when>
		<c:when test="${v.first}"><div class="detailfir"></c:when>
		<c:when test="${v.last}"><div class="detaillas"></c:when>
		<c:otherwise><div class="detail"></c:otherwise>
		</c:choose>
			<br><br><img src="${pageContext.request.contextPath}/common/down?filePath=fix/image&sName=${a.sysName }&dName=${a.oriName }" width="<c:if test="${a.width > 500 }">500</c:if>">
			<br>${content[v.index] }<br>
		</div>
	</c:forEach>
	</center>
	
	<c:import url="dut?no=${fix.no }" />
	
	<div class="LowBar">
		<a href="delete?no=${fix.no}">삭제</a>	
		<a href="modify?no=${fix.no}">수정</a>	
	</div>
</div>
<div class="bottom">
		<c:import url="/include/bottom.jsp" />
	</div>	