<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border=1 width=100%>
<form action="dutwrite" method="post">
				<tr>
					<td width="150">작성자: <input type="text" name="writer" size="3" value="${user.name }">
					<input type="hidden" name=no value="${fix.no }"></td>
					<td width>덧글: <input type="text" name="content" style="width: 90%"></td>
					<td width=100><button>제출</button></td>
				</tr>
</form>
				<c:if test="${empty list == false}">
				<c:forEach items="${list }" var="dut">
				<tr>
					<td>
						작성자:${dut.content }
					</td>
						<form action="dutmodify" method="post">
						<c:choose>
							<c:when test="${cno == dut.commentNo  && dut.id == user.id}">
								<td>
								<input type="hidden" name="no" value="${fix.no}">
								<input type="hidden" name="cno" value="${dut.commentNo}">
								내용:<input type="text" name="content" value="${dut.content }">
								</td>
								<td>
								<button>수정</button>
							</c:when>

							<c:otherwise>
								<td>
								내용:${dut.content }
								</td>
							
								<td>	
								<a href="dutdelete?cno=${dut.commentNo}&no=${fix.no}">삭제</a>
								<a href="?cno=${dut.commentNo}&no=${fix.no}">수정</a>
							</c:otherwise>
					</c:choose>
					</td>
					</form>
				</tr>
				</c:forEach>
				</c:if>
			</table>