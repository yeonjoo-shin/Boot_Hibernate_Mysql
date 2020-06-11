<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <h1>회원가입</h1>
  <form:form modelAttribute="memberVO" action="memberJoin" method="post" enctype="multipart/form-data">
  
  <form:input path="" type="hidden" />
  
  
  	 <div class="form-group">
      <label  for="pic">Avatar:</label>
      <input type="file" class="form-control" id="pic" placeholder="Enter pic" name="files" > <!-- name이름이 매개변수의 변수명과 같아야함 -->
    </div>
  
   <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID:</label>
      <div class="col-sm-10">
        <form:input path="id" type="text" class="form-control" id="id" placeholder="Enter Id"  /><br>
        <form:errors path="id" ></form:errors>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">Password:</label>
      <div class="col-sm-10">          
        <form:input path="pw" type="password" class="form-control" id="pw" placeholder="Enter password"  /><br>
         <form:errors path="pw"></form:errors>
      </div>
    </div>
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="pwCheck">PasswordCheck:</label>
      <div class="col-sm-10">          
        <form:input path="pwCheck" type="password" class="form-control" id="pwCheck" placeholder="Enter password"  /><br>
         <form:errors path="pwCheck"></form:errors>
      </div>
    </div>
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">          
        <form:input path="name" type="name" class="form-control" id="name" placeholder="Enter name" /><br>
         <form:errors path="name"></form:errors>
      </div>
    </div>
    
    
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="age">Age:</label>
      <div class="col-sm-10">          
        <form:input path="age" type="age" class="form-control" id="age" placeholder="Enter age" /><br>
         <form:errors path="age"></form:errors>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">email:</label>
      <div class="col-sm-10">          
        <form:input path="email" type="email" class="form-control" id="email" placeholder="Enter email" />
         <form:errors path="email"></form:errors>
      </div>
    </div>
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox" name="remember" value="remember"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
    
    

  </form:form>
</div>
	
	
	     

</body>
</html>