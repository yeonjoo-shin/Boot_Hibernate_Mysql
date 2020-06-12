<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/summer.jsp"></c:import>
</head>
<body>
<c:import url="../template/nav.jsp"></c:import>

<div class="container">
<h2>${board}Select</h2>
	<div class="panel panel-info">
		<div class="panel-heading">Title</div>
		<div class="panel-body"> ${vo.title }</div>
		<div class="panel-heading"> Writer</div> 
		<div class="panel-body">${vo.writer }</div>
		<div class="panel-heading"> Contents </div> 
		<div class="panel-body"> ${vo.contents } </div> 
	</div>

		<c:forEach items="${vo.boadFiles}" var="fileVO">
			<h2>${fileVO.oriName}</h2>
			<img src="../upload/${board}/${fileVO.fileName}"/>
		</c:forEach>



<a href="${board}Update?num=${vo.num}" class="btn btn-primary">Update</a>
<a href="${board}Delete?num=${vo.num}" class="btn btn-danger">Delete</a>
<a href="${board}Reply?num=${vo.num}" class="btn btn-info">Reply</a>

 </div>
</body>
</html>