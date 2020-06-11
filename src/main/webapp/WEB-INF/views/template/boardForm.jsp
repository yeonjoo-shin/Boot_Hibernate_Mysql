<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>






	<form action="${board}${path}" method="post" enctype="multipart/form-data">
	
	<input type="hidden" name="num" value="${vo.num}" id="num">
	<input type="hidden" name="vo" value="${vo}"><!-- boardVO자체를 보내줘야함 -->
	
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <input type="text" class="form-control" id="title" name="title" value="${vo.title}">
	  </div>
	  <div class="form-group">
	    <label for="writer">Writer:</label>
	    <input type="text" class="form-control" id="writer" name="writer" value="${member.id}">
	  </div>
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <textarea rows="" cols="" class="form-control" id="summernote" name="contents" >${vo.contents}</textarea>
	 
	  </div>
	  
	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f"> 
	  
	  </div>
	  <button type="submit" class="btn btn-default">Submit</button>
	</form>

	
<script type="text/javascript">
$(document).ready(function() {
	 	
	  $('#summernote').summernote({
		  height : 400
		  });
		
	
});

</script>