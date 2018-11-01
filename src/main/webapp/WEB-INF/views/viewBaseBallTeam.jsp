<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>プロ野球セントラルリーグ一覧</h1>

<c:forEach var="team" items="${teamList}">
<a href="${pageContext.request.contextPath}/BaseBallTeam/viewTeamDetail?id=${team.id}">${team.teamName}</a>
<br>
</c:forEach>
</body>
</html>