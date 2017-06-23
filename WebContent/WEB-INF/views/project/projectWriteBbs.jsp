<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
<!-- include summernote-ko-KR -->
<script src="lang/summernote-ko-KR.js"></script>

<title>Insert title here</title>
<style type="text/css">
div.layer{
	width: 80%;
	margin: auto;
}
div.writeform{
	width: 50%;
	margin: auto;
}
hr{
	width: 100%;
}
a.btn{
	width: 100%;
}
.topmenu{
	width: 20%;
}
</style>
</head>
<body>
<jsp:include page="../login/doLogout.jsp"></jsp:include> <!--로그아웃체크  -->
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<div class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>BBS</b></h4>
	</div>
	<hr> <!-- 공통 css -->
	<div class="writeform">
		<label>BBS Write</label>
		<hr>
		<label>title</label>
		<input type="text" class="form-control">
		<label>Contents</label>
		<div id="summernote"></div>
		<div style="margin-left: 25%;">
			<a href="projectNoticeBbs.jsp" class="btn btn-default" style=" width: 30%;">Write</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="projectNoticeBbs.jsp" class="btn btn-default" style=" width: 30%;">Cancel</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$('#summernote').summernote({
	  height: 300,
	  lang: 'ko-KR',				
	  minHeight: null,             
	  maxHeight: null,             
	  focus: true                  
	});
	
$("#logout").click(function() {
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href="logout.do";
		}
});
</script>
</html>