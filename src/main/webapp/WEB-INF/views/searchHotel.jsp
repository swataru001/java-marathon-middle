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
<h2>ホテル検索</h2>

<form:form modelAttribute="moneyForm" action="${pageContext.request.contextPath}/HotelSearch/searchResult">
一泊料金<form:input path="money"/>円以下
<input type="submit" value="検索">
</form:form>
<br>
<br>

<c:forEach var="hotel" items="${hotelList}">
<table border="1">
<tr>
<td>ホテル名</td>
<td><c:out value="${hotel.hotelName}"/></td>
</tr>
<tr>
<td>最寄り駅</td>
<td><c:out value="${hotel.nearestStation}"/></td>
</tr>
<tr>
<td>価格</td>
<td><c:out value="${hotel.price}"/></td>
</tr>
</table>
<br>
</c:forEach>
</body>
</html>