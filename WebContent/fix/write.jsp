<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
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
.content{margin-top: 50px;}
</style>
<script src="http://code.jquery.com/jquery-2.1.4.js"></script>
<script>
function hh(a){
	$('.hidden'+a).css('display','block');
	$('.button'+a).css('display','none');
	document.getElementsByName("hiddenfile"+(a))[0].name="file"+(a+1);
	document.getElementsByName("hiddencontent"+(a+1))[0].name="content"+(a+1);
}

function chk() {
	var IMG_FORMAT = "\.(bmp|gif|jpg|jpeg|png)$";

	if(document.form1.file1){
		if(!new RegExp(IMG_FORMAT, "i").test(document.form1.file1.value)) {
	    	alert("111파일은 필수 첨부입니다.(이미지 파일 외 입력 불가)");
	    	return false;
		} else {
			if(document.form1.file2){
				if(!new RegExp(IMG_FORMAT, "i").test(document.form1.file2.value)) {
			    	alert("222파일은 필수 첨부입니다.(이미지 파일 외 입력 불가)");
			    	return false;
				} else {
					if(document.form1.file3){
						if(!new RegExp(IMG_FORMAT, "i").test(document.form1.file3.value)) {
					    	alert("333파일은 필수 첨부입니다.(이미지 파일 외 입력 불가)");
					    	return false;
						} else {
							if(document.form1.file4){
								if(!new RegExp(IMG_FORMAT, "i").test(document.form1.file4.value)) {
							    	alert("444파일은 필수 첨부입니다.(이미지 파일 외 입력 불가)");
							    	return false;
								} else {
									if(document.form1.file5){
										if(!new RegExp(IMG_FORMAT, "i").test(document.form1.file5.value)) {
									    	alert("555파일은 필수 첨부입니다.(이미지 파일 외 입력 불가)");
									    	return false;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
    
	if(document.form1.content1.value == '내용을 입력하세요.' || !document.form1.content1.value) {
    	alert("텍스트 문서가 비었습니다."); return false;}

    if(!document.form1.content2.value) {
    	alert("텍스트 문서가 비었습니다."); return false;}
    
    if(!document.form1.content3.value) {
    	alert("텍스트 문서가 비었습니다."); return false;}
    
    if(!document.form1.content4.value) {
    	alert("텍스트 문서가 비었습니다."); return false;}
    
    if(!document.form1.content5.value) {
    	alert("텍스트 문서가 비었습니다."); return false;}
    
    return true;
}
</script>

<c:choose>
<c:when test="${not empty user.id}">
<script>
alert("로그인을 해주세요.");
location.href='list';
</script>
</c:when>

<c:otherwise>
<div class="header">
		<c:import url="/include/topMenu.jsp" />
</div>
<div class="content">
	<form action="${pageContext.request.contextPath }/fix/writeinsert" method="post"
	enctype="multipart/form-data" name="form1" onsubmit="return chk()" style="height: 800px;">
	
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
		<textarea name="content1" style="height: 50px;"
		onclick="if (this.value == '내용을 입력하세요.') {this.value = ''}">내용을 입력하세요.</textarea>
	</div>
	
	<div class="button1">
		<button type="button" onclick="hh(1)">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden1">
	<div class="input">
		<div class="filebox">
			<input type="file" name="hiddenfile1">
		</div>
	</div>
	
	<div class="input">
		<textarea name="hiddencontent2" style="height: 50px;"></textarea>
	</div>
	
	<div class="button2">
		<button type="button" onclick="hh(2)">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden2">
	<div class="input">
		<div class="filebox">
			<input type="file" name="hiddenfile2">
		</div>
	</div>
	
	<div class="input">
		<textarea name="hiddencontent3" style="height: 50px;"></textarea>
	</div>
	
	<div class="button3">
		<button type="button" onclick="hh(3)">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden3">
	<div class="input">
		<div class="filebox">
			<input type="file" name="hiddenfile3">
		</div>
	</div>
	
	<div class="input">
		<textarea name="hiddencontent4" style="height: 50px;"></textarea>
	</div>
	
	<div class="button4">
		<button type="button" onclick="hh(4)">내용 추가하기</button>
	</div>
	</div>
	
	<div class="hidden4">
	<div class="input">
		<div class="filebox">
			<input type="file" name="hiddenfile4">
		</div>
	</div>
	
	<div class="input">
		<textarea name="hiddencontent5" style="height: 50px;"></textarea>
		<br>이미지는 최대 5개만 첨부됩니다.
	</div>
	</div>

	<div class="submit">
		<input type="submit">
	</div>
	
	</form>
</div>
</c:otherwise>

</c:choose>
<div class="bottom">
		<c:import url="/include/bottom.jsp" />
</div>	