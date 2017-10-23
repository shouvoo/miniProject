<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 글쓰기</title>
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
	<div>
		top메뉴	
	</div>
	<form action="${pageContext.request.contextPath}/qna/write" method="post">
	<div>
		<table>
			<tbody>
				<tr>
					<th>제목 :</th>
					<td><input type="text" name="title"/></td>
				</tr>
				<tr>
					<th>글쓴이 :</th>
					<td><input type="text" name="writer"/></td>
				</tr>
				<tr>
					<th>내용 :</th>
					<td><textarea cols="80" rows="6" name="content"
					>내용을 입력해주세요</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><button type="submit" >등록</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	
	<div>
		footer
	</div>
	
	
</body>
</html>