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

<style type="text/css">
	.r{
		color: red;
	}
</style>

</head>
<body>
<c:import url="../template/nav.jsp"></c:import>
<div class="container"> 
	<h2>${board}${path}</h2>
	<c:import url="../template/springForm.jsp">
	
	</c:import>

</div>
<script type="text/javascript">

	//글 쓸때 num이 ''이 되므로 num을 0으로 만들어주거나 삭제시켜줌.
	//글 업데이트를 위해 num을 hidden으로 숨겨서 가져옴
	var board ='${path}';

	if(board=='Write'){
		//$("#num").val(0);
		$("#num").remove(); 
	}

	$("#add").click(function(){
	 $("#f").append('<input type="file" name="files">');

	});
</script>
</body>
</html>