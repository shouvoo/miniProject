<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 목록</title>
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
	<div>top메뉴</div>
	<div style="text-align: right">전체${pageResult.count}개</div>
		<div>
			<table>
				<tbody>
					<tr>
						<td>글번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일자</td>
						
					</tr>
					<c:forEach var="qna" items="${list}">
						<tr>
							<td>${qna.no}</td>
							<td><a href="${pageContext.request.contextPath}/qna/detail?no=${qna.no}">${qna.title}</a></td>
							<td>${qna.writer}</td>
							<td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<c:if test="${not empty qna.aComment}">
							
							<tr>
								<td></td>
								<td>
									<img alt="" src="../image/qna/reple.gif">
									<a href="${pageContext.request.contextPath}/qna/answerDetail?no=${qna.no}">${qna.title}에 대한 답글 입니다.</a>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<navi:page data="${pageResult}"/>
		</div>
		<div>
			<form action="${pageContext.request.contextPath}/qna/writeform">
				<button>글쓰기</button>
			</form>
		</div>
	<div>footer</div>
</body>
</html>