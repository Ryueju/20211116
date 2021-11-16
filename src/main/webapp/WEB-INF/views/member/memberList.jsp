<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
	<div align="center">
		<div><h1>멤 버 목 록</h1></div>
		<div>
			<table border="1">
				<tr>
					<th width="200"> 아이디</th>
					<th width="150"> 이 름</th>
					<th width="200"> 연락처</th>
					<th width="400"> 주 소</th>
					<th width="150"> 권 한</th>
					
				</tr>
				<c:forEach items="${members }" var="member">
				
					<tr>
						<td>${member.id }</td>
						<td>${member.name }</td>
						<td>${member.tel }</td>
						<td>${member.address }</td>
						<td>${member.author }</td>
					</tr>
				</c:forEach>
				
				<div>
				<button type="button" onclick="location.href='home.do'">홈으로</button>
				</div>
				 
			</table>
		</div>
		<%-- <c:forEach items="${members }" var="member">
			<!-- 			ㄴ한 행을 member.id/member.name이런식으로 읽게됨. 
			id,password,name은 VO객체 있는 변수명과 동일해야함. -->
			<!-- 이 안의 부분의 반복하는 것 -->
			${member.id } : ${member.password } : ${member.name }<br>
			
		</c:forEach> --%>
	</div>
</body>
</html>