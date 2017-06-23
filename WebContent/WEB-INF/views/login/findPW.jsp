<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login Page</title>
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/>
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
		<div>
			<label>E-mail</label>
			<input type="email" class="form-control" name="im_email">
		</div>
		<br>
		<div>
			<a href="#none" id="getPw" class="btn btn-default" style="width: 30%;">Find</a>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
	$("#getPw").click(function() {
		$("#findfrm").attr({"target" : "_self" , "action" : "getFindPW.do"}).submit();
	});
</script>
</html>