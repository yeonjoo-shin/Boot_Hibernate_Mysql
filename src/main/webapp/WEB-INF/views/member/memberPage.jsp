<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
	
<c:import url="../template/nav.jsp"></c:import>
	<div class="container">
  <h2>MY PAGE</h2>
  
  <form action="./memberPage" method="post"  >
  	
  	<img alt="" src="../upload/member/${member.memberFileVO.fileName}"> <!-- resouces/static은 안써두됭 -->
  	
    <div class="form-group">
      <label for="Id">ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter ID" name="id" value="${member.id}">
    </div>
       
    
    <div class="form-group">
      <label for="Name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="uname" value="${member.name}">
    </div>
    
     <div class="form-group">
      <label for="Age">Age:</label>
      <input type="text" class="form-control" id="age" placeholder="Enter Age" name="age" value="${member.age}">
    </div>
    
     <div class="form-group">
      <label for="Email">email:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email" value="${member.email}">
    </div>
    
    <div class="form-group">
      <label for="Phone">phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone" value="${member.phone}" >
    </div>
    
    
    
  	
  </form>
  
   <button class="btn btn-primary" id="up" > Update </button>
   <button class="btn btn-danger" id="del" > Delete </button>
   
   <script type="text/javascript">
	$("#del").click(function () {
		var result = window.confirm("탈퇴 하겠습니까?");
		if(result){
			location.href="./memberDelete";
		}
	});
	
	$("#up").click(function() {
		var result = window.confirm("수정 하시겠습니까?");
		if(result){
			location.href="./memberUpdate"; //버튼을 누리면 update.jsp 페이지로 이동
		}
		
	});
	
	
	
	</script>
   
</div>
	
	
	    
</body>
</html>