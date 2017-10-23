<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 답글</title>
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
			</tbody>
		</table>
	</div>
		<form action="${pageContext.request.contextPath}/qna/answer" method="post">
		<br>
		<div>
			<h3>
				답글내용
			</h3>
			<input type="hidden" name="no" value="${qna.no}">
		</div>
		<div>
		<table>
			<tbody>
				
				<tr>
					<th>내용 :</th>
					<td>
					<textarea cols="80" rows="6" name="aComment">${qna.aComment}</textarea>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><button type="submit" >등록</button></td>
				</tr>
			</tbody>
		</table>
		</div>
		</form>
	
	<div>footer</div>
</body>
</html>