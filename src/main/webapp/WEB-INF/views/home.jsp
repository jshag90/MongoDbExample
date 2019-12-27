<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>몽고DB CRUD 테스트 페이지</title>
<link href="./resources/common.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form id="car_form" action="./insert_car">
		<table>
			<tbody>
				<tr>
					<td>브랜드 :</td>
					<td><input name="brand"></input></td>
				</tr>
				<tr>
					<td>모델 :</td>
					<td><input name="model"></input></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">저장하기</button></td>
				</tr>
			</tbody>
		</table>

	</form>
	
<form action="./delete_all_car"><button type="submit">모두삭제</button></form>

	<table >
		<thead>
			<tr>
				<th>_id</th>
				<th>브랜드</th>
				<th>모델</th>
				<th></th>
				<th></th>
			</tr>
		<thead>
		<tbody>
			<c:forEach var="car" items="${CARS}" varStatus="status">
				<tr>
					<td>${car.id}</td>
					<td>${car.brand}</td>
					<td>${car.model}</td>
					<td><a href="./findOneCarData?id=${car.id}">수정</a></td>
					<td><a href="./delete_car?brand=${car.brand}&model=${car.model}">삭제</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table><br/>
	

</body>
</html>
