<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.TopBar{font: 20px bold; color: #0085D5; background: rgb(239, 239, 239); width: 600px; position: fixed;
height: 27px;}
.boardLike{float: right;}
.LowBar{font: 20px bold; color: #0085D5; background: rgb(239, 239, 239); width: 600px;}
.LowBar *{text-align: right;}
.boardCate{font-size: 13px;}
.SecondBar{font: 15px; color: black; width: 600px; margin-bottom: 10px; border: 1px solid black;
position: fixed; margin-top: 29px;}
.boardRegDate{float: right;}
.detail{margin-bottom: 20px;}
.board{width: 100%; height: auto; overflow: auto; border: 2px thin black; padding-top: 60px;}
</style>
<div class="content">
	<div class="TopBar">
		<span class="boardCate">${fix.cate }>${fix.brand }</span>
		<span class="boardTitle">${fix.title }</span>
		<span class="boardLike">추천: ${fix.like }개</span>
	</div>
	
	<div class="secondBar">
		<span class="boardWriter">작성자: ${fix.writer }</span>
		<span class="boardRegDate">작성일: <fmt:formatDate value="${fix.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></span>
	</div>

	<c:forEach items="${file }" var="a" varStatus="v">
		<div class="detail">
			<br><br><br><img src="${pageContext.request.contextPath}/common/down?filePath=fix/image&sName=${a.sysName }&dName=${a.oriName }" width="35%">
			<br>${content[v.index] }
		</div>
	</c:forEach>
	
	<c:import url="dut?no=${fix.no }" />
	
	<div class="LowBar">
		<a href="delete?no=${fix.no}">삭제</a>	
		<a href="premodify?no=${fix.no}">수정</a>	
		<a href="like?no=${fix.no}">추천</a>	
	</div>
</div>