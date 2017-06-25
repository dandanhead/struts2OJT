<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<fmt:requestEncoding value ="utf-8"/>
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
		<input type="text" class="form-control" name="mvo.im_name" >
		<div>
			<label>Social Number</label>
			<div class="row">
			  <div class="col-xs-6">
			    <input type="text" class="form-control" placeholder="앞 자리" name="startnum" maxlength="6">
			  </div>
			  <div class="col-xs-6">
			    <input type="password" class="form-control" placeholder="뒷 자리" name="endnum" maxlength="7">
			  </div>
			</div>
		</div>
		<div>
			<label>E-mail</label>
			<input type="email" class="form-control" name="mvo.im_email">
		</div>
		<br>
		<s:fielderror/>
		<div>
			<a href="#none" id="getPw" class="btn btn-default" style="width: 30%;">Find</a>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
	$("#getPw").click(function() {
		$("#findfrm").attr({"target" : "_self" , "action" : "getFindPw"}).submit();
	});
</script>
</html>