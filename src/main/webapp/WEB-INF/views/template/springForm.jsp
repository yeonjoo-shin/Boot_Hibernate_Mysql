<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>



<form:form modelAttribute="boardVO"  action="${board}${path}" method="post" enctype="multipart/form-data">
	
	<form:input path="num"   name="num"  id="num" />
	<!--<input type="hidden" name="vo" value="${vo}"/>-- boardVO자체를 보내줘야함 -->
	
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <form:input  path="title"  type="text" class="form-control" id="title"   />
	    <form:errors path="title" cssClass="r"></form:errors>
	    <!-- path 는 boardvo의 변수명입력  value가 필요없음 , name도 필요없음 파라미터 안써도됨-->
	  </div>
	  <div class="form-group">
	    <label for="writer">Writer:</label>
	    <form:input path="writer" type="text" class="form-control" id="writer"  />
	     <form:errors path="writer"></form:errors>
	  </div>
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <form:textarea path="contents" rows="" cols="" class="form-control" id="summernote"  ></form:textarea>
	     <form:errors path="contents"></form:errors>
	 
	  </div>
	  
	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f"> 
	  
	  </div>
	  <button type="submit" class="btn btn-default">Submit</button>
</form:form>

