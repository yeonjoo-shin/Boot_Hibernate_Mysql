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
  <h2>my date update</h2>
  
  <form action="./memberUpdate" method="post">
  
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
    
 
      
      <button type="submit" class="btn btn-primary" id="up" >Submit</button>
  </form>
  
   <script type="text/javascript">
	
	$("#up").click(function() {
		var result = window.confirm("수정 하시겠습니까?");
		if(result){
			location.href="./memberUpdate";
		}
		
	});
	
	
	
	</script>
   
</div>
	
</body>
</html>