<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/> 
<title>login Page</title>
<style type="text/css">
div.layer{
	width: 50%;
	margin: auto;
}
</style>
</head>
<body>
<form method="post" id="findfrm">
	<div class="layer">
		<label>Name</label>
		<input type="text" class="form-control" name="im_name">
		<div>
			<label>Social Number</label>
			<div class="row">
			  <div class="col-xs-6">
			    <input type="text" class="form-control" placeholder="앞 자리" name="f_num">
			  </div>
			  <div class="col-xs-6">
			    <input type="text" class="form-control" placeholder="뒷 자리" name="e_num">
			  </div>
			</div>
		</div>
		<br>
		<div>
			<a href="#none" id="getId" class="btn btn-default" style="width: 30%;">Find</a>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
	$("#getId").click(function() {
		$("#findfrm").attr({"target" : "_self" , "action" : "getFindID.do"}).submit();
	});
</script>
</html>