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
		<input type="text" class="form-control" name="mvo.im_name" id="inputname" value="${mvo.im_name}">
		<div>
			<label>Social Number</label>
			<div class="row">
			  <div class="col-xs-6">
			    <input type="text" class="form-control" placeholder="앞 자리" name="startnum" id="inputstartnum" value="${startnum}">
			  </div>
			  <div class="col-xs-6">
			    <input type="password" class="form-control" placeholder="뒷 자리" name="endnum" id="inputendnum" maxlength="7">
			  </div>
			</div>
			<b style="color: red;"><s:fielderror fieldName="errmsg"/></b>
		</div>
		<br>
		<div>
			<a href="#none" id="getId" class="btn btn-default" style="width: 30%;">Find</a>
		</div>
	</div>
	<input type="hidden" name="alertmsg" id="alertmessage" value="${alertmsg}"/>
</form>

</body>
<script type="text/javascript">
	$(function() {
		var msg = $("#alertmessage").val();
		if( msg!= null && msg != ""){
			alert(msg);
		}
	});
	$("#inputstartnum").on("keyup", function() {
		if($(this).val().length > 6){
			$(this).val($(this).val().substring(0 , 6));
		}
	});
	
	$("#getId").click(function() {
		$("#findfrm").attr({"target" : "_self" , "action" : "getFindId"}).submit();
	});
</script>
</html>