<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
.content{margin-top: 50px;}
.writeTitle{font: 20px bold; color: #0085D5; background: rgb(239, 239, 239); width: 100%;
margin-bottom: 10px;}
.input *, .select *{height: 27px; margin: 5px;}
.input *{border: 1px solid black; }
.input span{border-bottom-left-radius: 5px; border-top-left-radius: 5px;
display: inline-block; width: 50px;}
.input input{border-bottom-right-radius: 5px; border-top-right-radius: 5px;}
.hh, .hidden1, .hidden2, .hidden3, .hidden4 {border: 2px solid black; width: 666}
.hidden1, .hidden2, .hidden3, .hidden4 {display: none;}
.submit{margin-top: 10px;}
textarea {width: 500px;}
</style>

<c:choose>
<c:when test="${user.id == fix.id}">
<script>
alert("로그인을 해주세요.");
</script>
</c:when>

<c:otherwise>
<div class="header">
		<c:import url="/include/topMenu.jsp" />
</div>
<div class="content">
	<form action="${pageContext.request.contextPath }/fix/modifyinsert" method="post"
	enctype="multipart/form-data">
	
	<div class="writeTitle">
		수정
	</div>
	
	<div class="input">
		<span>제목</span>:
		<input type="text" name="title" value="${fix.title }">
		<input name="no" value="${param.no }" type="hidden">
	</div>
	
	<div class="input">
		<span>작성자</span>:
		<input type="text" value="${user.name }" name="writer" readonly="readonly">
	</div>
	
	<div class="select">
		<span><select name="cate">
			<option value="휴대전화">휴대전화</option>
			<option value="노트북">노트북</option>
			<option value="데스크탑">데스크탑</option>
			<option value="디스플레이">디스플레이</option>
			<option value="콘솔">콘솔</option>
			<option value="가전제품">가전제품</option>
		</select></span>

		<span><select name="brand">
			<option value="삼성">삼성</option>
			<option value="엘지">엘지</option>
			<option value="애플">애플</option>
			<option value="도시바">도시바</option>
			<option value="샤오미">샤오미</option>
			<option value="기타">기타</option>
		</select></span>
	</div>

	<c:forEach items="${file }" var="var" varStatus="a">
	<div class="input">
		<img src="${pageContext.request.contextPath}/common/down?filePath=fix/image&sName=${var.sysName }&dName=${var.oriName }">
		${var.oriName }</br>
		<input type="file" name="${a.index }">
		<input type="hidden" name="${a.index }name" value="${var.sysName }">		
		<input type="hidden" name="${a.index }no" value="${var.imageNo }">		
		<textarea name="content" style="height: 50px;"
		onclick="if(this.value == ${a}){this.value=''}">${content[a.index] }</textarea>
	</div>
	</c:forEach>

	<div class="submit">
		<button type="submit">제출</button>
	</div>
	
	</form>
</div>
</c:otherwise>

</c:choose>
<div class="bottom">
		<c:import url="/include/bottom.jsp" />
	</div>	
<script>
/* console.dir($('select[name="cate"]')); */
$('select[name="cate"]').val('${fix.cate}');
$('select[name="brand"]').val('${fix.brand}');
</script>