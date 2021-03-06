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

	<form id="car_form" method="post" action="./insert_car">
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
	
<form action="./delete_all_car" method="post" ><button type="submit">모두삭제</button></form>

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
					<td><a href="./find_one_car?id=${car.id}">수정</a></td>
					<td><a href="javascript:deleteCar('${car.brand}','${car.model}');">삭제</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table><br/>
	
	<script type="text/javascript">
		function deleteCar(brand, model){
			var form = document.createElement("form");

			form.setAttribute("charset", "UTF-8");
			form.setAttribute("method", "POST");
			form.setAttribute("action", "./delete_car");
			
			var hiddenField = document.createElement("input");

			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "brand");
			hiddenField.setAttribute("value", brand);

			form.appendChild(hiddenField);
			
			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "model");
			hiddenField.setAttribute("value", model);
			form.appendChild(hiddenField);
			
			document.body.appendChild(form);
			
			form.submit();
			
		}
	</script>

</body>

</html>
