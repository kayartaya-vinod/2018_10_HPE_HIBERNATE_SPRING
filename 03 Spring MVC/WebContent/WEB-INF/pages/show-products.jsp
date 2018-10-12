<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of products</title>
</head>
<body>
	<h1>List of products</h1>
	<hr>
	<p><a href="./">Home</a></p>
	
	<table border="1">
		<thead>
			<tr>
				<th>Sl no</th>
				<th>Product description</th>
				<th>Quantity per unit</th>
				<th>Unit price</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="p" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${p.description}</td>
					<td>${p.quantityPerUnit}</td>
					<td>${p.unitPrice}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>