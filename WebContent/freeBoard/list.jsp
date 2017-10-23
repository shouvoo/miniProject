
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FIX</title>
<%@ include file="/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainLayout.css" type="text/css">
<script type="text/javascript">

	function buttonClick(link)
	{	
		location.href = link;
	}
	
</script>

</head>
<body>
<div class="container">
	<div>
	  <img alt="full screen background image" src="${pageContext.request.contextPath}/img/freeBoard/freeBoard.jpg" id="full-screen-background-image" /> 

	</div>

	<div class="header">
		<c:import url="/include/topMenu.jsp" />
	</div>

	<div class="content">
	  <div id="wrapper">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
			</tr>
			<div>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</div>
			<div>
				<input  type="button" value="수정" onclick="buttonClick('http://www.naver.com');" />
				<input  type="button" value="삭제" onclick="buttonClick('www.daum.net');" />
				<input  type="button" value="목록" onclick="buttonClick('www.nate.com');" />
			</div>
		</div>
	</div>	
	
	<div class="bottom">
		<c:import url="/include/bottom.jsp" />
	</div>	
</div>


</body>
</html>


















