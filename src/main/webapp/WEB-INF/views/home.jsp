<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form id="car_form" action="./insert_car">
	브랜드 : <input name = "brand"></input><br/>
	모델 : <input name = "model"></input><br/>
	<button type="submit">저장하기</button>
</form>
<form  action="./delete_all_car">
	<button type="submit">모두삭제</button>
</form>

	=Collection 데이터 조회<br/>
<%-- 	IDS : ${IDS}<br/> --%>
<%-- 	BRANDS : ${BRANDS}<br/> --%>
<%-- 	MODELS : ${MODELS}  --%>

<c:forEach var="car" items="${CARS}" varStatus="status">     
<a href="./findOneData?brand=${car.brand}&model=${car.model}&id=${car.id}">${car.id}|${car.brand}|${car.model}</a></br>
</c:forEach>

</body>
</html>
