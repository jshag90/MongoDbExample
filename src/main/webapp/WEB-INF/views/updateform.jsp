<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="car_form" action="./update_car">
	
	<table>
			<tbody>
				<tr>
					<td>브랜드 :</td>
					<td><input name="brand_modify" value="${CAR.brand}" ></input></td>
				</tr>
				<tr>
					<td>모델 :</td>
					<td><input name="model_modify"  value="${CAR.model}"></input></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">수정하기</button></td>
				</tr>
			</tbody>
		</table>
		
		<input name = "brand" type="hidden" value="${CAR.brand}"/>
		<input name = "model" type="hidden" value="${CAR.model}"/>
		<input name = "id" type="hidden" value="${CAR.id} }"/>
</form>
</body>
</html>