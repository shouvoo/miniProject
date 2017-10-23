<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 상세</title>
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
	<div>top메뉴</div>
		<div>
		<table>
			<tbody>
				<tr>
					<th>글번호 : </th>
					<td>${qna.no}</td>
				</tr>
				<tr>
					<th>제목 :</th>
					<td>${qna.title}</td>
				</tr>
				<tr>
					<th>글쓴이 :</th>
					<td>${qna.writer}</td>
				</tr>
				<tr>
					<th>내용 :</th>
					<td>${qna.content}</td>
				</tr>
				
				<tr>
					<td colspan="2" align="right">
					<a href="${pageContext.request.contextPath}/qna/list">목록</a>
					<a href="${pageContext.request.contextPath}/qna/answerForm?no=${qna.no}">답글</a>
					<a href="${pageContext.request.contextPath}/qna/modifyForm?no=${qna.no}">수정</a>
					<a href="${pageContext.request.contextPath}/qna/delete?no=${qna.no}">삭제</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<c:if test="${not empty qna.aComment}">
		<div>
			<h3>
				답글내용
			</h3>
		</div>
		<div>
		<table>
			<tbody>
				
				<tr>
					<th>내용 :</th>
					<td>
					${qna.aComment}
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</c:if>
	
	<div>footer</div>
</body>
</html>