<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>洋服SHOP</h2>
<p>好きな色を入力してください</p><br>
性別<br>
<form:form modelAttribute="shopForm" action="${pageContext.request.contextPath}/itemSearch/searchResult">
<form:radiobuttons path="gender" items="${genderMap}"/>
好きな色
<form:select path="color" items="${colorMap}" delimiter ="<br>"/>
<input type="submit" value="検索">
</form:form>

<c:forEach var="item" items="${itemList}">
<table border="1">
<tr>
<td>ジャンル</td>
<td><c:out value="${item.genre}"/></td>
</tr>
<tr>
<td>サイズ</td>
<td><c:out value="${item.size}"/></td>
</tr>
<tr>
<td>価格</td>
<td><c:out value="${item.price}"/></td>
</tr>
</table>
<br>
</c:forEach>
</body>
</html>