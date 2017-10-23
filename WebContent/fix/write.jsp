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
.filebox, .filebox1, .filebox2, .filebox3, .filebox4 {border: none;}
#ex_filename {display: none;}
</style>
<script src="http://code.jquery.com/jquery-2.1.4.js"></script>
<script type="text/javascript">
</script>

<c:choose>
<c:when test="${not empty user.id}">
<script>
alert("로그인을 해주세요.");
location.href='list';
</script>
</c:when>

<c:otherwise>
<div class="content">
	<form action="${pageContext.request.contextPath }/fix/writeinsert" method="post"
	enctype="multipart/form-data">
	
	<div class="writeTitle">
		새로운 글
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
	
	<div class=hh>
	<div class="input">
		<div class="filebox">
			<input type="file" name="file1">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"
		onclick="if(this.value=='내용을 입력하세요.'){this.value=''}">내용을 입력하세요.</textarea>
	</div>
	
	<div class="button1">
		<button type="button" onclick="
		$('.hidden1').css('display','block');
		$('.button1').css('display','none');
		">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden1">
	<div class="input">
		<div class="filebox">
			<input type="file" name="file2">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"></textarea>
	</div>
	
	<div class="button2">
		<button type="button" onclick="
		$('.hidden2').css('display','block');
		$('.button2').css('display','none');
		">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden2">
	<div class="input">
		<div class="filebox">
			<input type="file" name="file3">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"></textarea>
	</div>
	
	<div class="button3">
		<button type="button" onclick="
		$('.hidden3').css('display','block');
		$('.button3').css('display','none');
		">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden3">
	<div class="input">
		<div class="filebox">
			<input type="file" name="file4">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"></textarea>
	</div>
	
	<div class="button4">
		<button type="button" onclick="
		$('.hidden4').css('display','block');
		$('.button4').css('display','none');
		">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden4">
	<div class="input">
		<div class="filebox">
			<input type="file" name="file5">
		</div>
	</div>
	
	<div class="input">
		<textarea name="content" style="height: 50px;"></textarea>
		<br>이미지는 최대 5개만 첨부됩니다.
	</div>
	</div>

	<div class="submit">
		<button type="submit">제출</button>
	</div>
	
	</form>
</div>
</c:otherwise>

</c:choose>