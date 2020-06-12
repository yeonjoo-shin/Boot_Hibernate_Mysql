<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
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
<h2>${board}List</h2>
<form class="form-inline" action="./${board}List" id="frm">
<input type="hidden" name="page" id="p">
		    <div class="input-group input-group-sm col-xs-2" >
		    
		    	<select class="form-control" id="sel1" name="kind">
				    <option value="title" id="title" selected="selected">Title</option>
				    <option value="contents" id="contents">Contents</option>
				    <option value="writer" id="writer">writer</option>
  				</select>
  				</div>
  				
  				<div class="input-group input-group-sm col-xs-4">
  				
			      <input type="text" class="form-control" placeholder="Search" name="search"  value="${param.search}">
			      <div class="input-group-btn">
			        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
			      </div>
	    		</div>
</form>


	<table class="table table-hover">
		<tr>
			<td>Num</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>
		
		<c:forEach items="${page.content}" var ="vo">
			<tr>
				<td>${vo.num}</td>
				<c:catch>
					<c:forEach begin="1" end="${vo.depth}">
						[답변]							
					</c:forEach>
				</c:catch>
				
				<td><a href="${board}Select?num=${vo.num}">${vo.title }</a></td>
				<td>${vo.writer }</td>
				<td>${vo.regDate }</td>
				<td>${vo.hit }</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
	
	<ul class="pagination">
		<c:if test="${pager.curBlock>1}">
			<li><a href="#" class="cpager" title="${pager.startNum-1}">이전</a></li>
		</c:if>
		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="p">
			<li><a href="#" class="cpager" title="${p}">${p}</a></li>
		</c:forEach>	
		
		<c:if test="${pager.curBlock<pager.totalBlock }">
			<li><a href="#" class="cpager" title="${pager.lastNum+1}">다음</a></li>
		</c:if>
	
	</ul>
	
	
	<!--  
	<span><a href="#" class="cpager" title="0"> &lt;&lt;</a></span>
	<span><a href="#" class="cpager" title="${page.number-1}">&lt;</a></span>
	<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
		<c:if test="${i lt page.getTotalPages()}">
			<a href=" #" class="cpager" title="${i}">${i+1}</a> 
		</c:if>
	</c:forEach>
	<span><a href="#" class="cpager" title="${page.number+1}">&gt;</a></span> 
	<span><a href="#" class="cpager" title="${page.getTotalPages()-1}"> &gt;&gt;</a></span>
	
	<hr>-->
	<!--  
	<c:if test="${not page.isFirst() }">
		<a href=" ./${board}List?page=${page.getNumber()-1 }">[이전]</a>
	</c:if>
		<span>${page.getNumber()+1}</span> <!-- 제일 첫번째 페이지가 0이 아니라 1로 보이고자함 
	<c:if test="${not page.isLast() }">
		<a href="./${board}List?page=${page.getNumber()+1 }">[다음]</a>
	</c:if>	
	</div> -->
	
	  <a href="./${board}Write" class="btn btn-danger">Write</a>
	<!--  
	<div>
		<ul class="pagination">
			<c:if test="${pager.curBlock gt 1 }">
				<li> <a href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.curBlock lt pager.totalBlock}">
			<li><a href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음 </a></li>
			</c:if>
		</ul>
	</div>
	-->
	
	
	
	
</div> 

<script type="text/javascript">

	$(".cpager").click(function(){
		var page = $(this).attr("title");//속성을 가지고오기 attr/prop //1234 를 가지고올것
		//alert(page);
		$("#p").val(page);//form이 전송되면 안에 p도 파라미터로 넘어감
		$("#frm").submit();//form 에 담겨서 넘어감//검색하는 폼
	
	});


	var kind = '${param.kind}';
	if(kind==''){
 		$("#title").prop("selected",true);
	}else{
		$("#"+kind).prop("selected",true);
	}









	var  result ='${result}'; 
	
	if(result !=''){
		if(result=='1'){
			alert("Write Success");
		}else{
			alert("Write Fail");
		}
	}

</script>
</body>
</html>