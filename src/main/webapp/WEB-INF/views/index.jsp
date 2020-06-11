<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./template/boot.jsp"></c:import>
<link href="./css/test.css" rel="stylesheet" type="text/css">
</head>

<body>
	<c:import url="./template/nav.jsp"></c:import>

	<div class="container"> 
		<h1>Index Page</h1>
		
		
		<img src="./upload/notice/00be37c8-2ad7-4a69-b975-7f8c523dc485_1590392806572l0.jpg">
		<h1>${test}</h1>
	</div>
	<script type="text/javascript" src="./js/test.js"></script>
	<script type="text/javascript">
		//start();
	</script>
</body>
</html>