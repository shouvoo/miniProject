<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
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
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
$('.option[value='${fix.cate}']').attr('selected', 'selected');
</script>

<c:choose>
<c:when test="${not empty user.id}">
<script>
alert("로그인을 해주세요.");
</script>
</c:when>

<c:otherwise>
<div class="content">
	<form action="${pageContext.request.contextPath }/fix/writeinsert" method="post"
	enctype="multipart/form-data">
	
	<div class="writeTitle">
		수정
	</div>
	
	<div class="input">
		<span>제목</span>:
		<input type="text" name="title">
	</div>
	
	<div class="input">
		<span>작성자</span>:
		<input type="text" value="${user.name }" name="writer" readonly="readonly">
	</div>

	<div class="select">
		<span><select name="cate" id="cate">
			<option value="휴대전화">휴대전화</option>
			<option value='노트북'>노트북</option>
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
	
	<div class=hh>
	<div class="input">
		<div class="filebox">
			<input class="upload-name" value="파일선택" disabled="disabled">
			<label for="ex_filename">업로드</label>
			<input type="file" id="ex_filename" class="upload-hidden" name="file1">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"
		onclick="if(this.value=='내용을 입력하세요.'){this.value=''}">내용을 입력하세요.</textarea>
	</div>
	</div>

	<div class="submit">
		<button type="submit">제출</button>
	</div>
	
	</form>
</div>
</c:otherwise>

</c:choose>