<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 수정</title>
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
<div>top메뉴</div>
	<div>
		<form action="${pageContext.request.contextPath}/qna/modify" method = "post">
			<input type="hidden" name="no" value="${qna.no}"/>
			<div>
				<table>
					<tbody>
						<tr>
							<th>제목 :</th>
							<td><input type="text" name="title" value="${qna.title}"/></td>
						</tr>
						<tr>
							<th>글쓴이 :</th>
							<td><input type="text" name="writer" value="${qna.writer}"/></td>
						</tr>
						<tr>
							<th>내용 :</th>
							<td><textarea cols="80" rows="6" name="content"
							>${qna.content}</textarea></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><button type="submit" >수정완료</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<div>footer</div>
</body>
</html>