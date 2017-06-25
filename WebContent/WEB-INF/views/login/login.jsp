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
div.form-group{
	width: 50%;
	text-align: center;
}
</style>
</head>
<body>
<br><br><br><br><br><br><br>
<div style="width: 720px; height: 480px; background-image: url('${pageContext.request.contextPath }/img/icanback.png'); margin: auto;">
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<s:form class="form-horizontal" style="margin-left: 200px;" id="loginFrm" method="post" action="loginAf">
  <div class="form-group">
    <label for="inputid" class="col-sm-2 control-label" style="color: white;">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputid" placeholder="input your ID" name="mvo.im_idx">
    </div>
  </div>
  <div class="form-group">
    <label for="inputpw" class="col-sm-2 control-label" style="color: white;">PW</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputpw" placeholder="input your Password" name="mvo.im_pw">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-default" style="width: 100%;" id="btnLogin" type="submit">Login</button>
      <a></a>
    </div>
  </div>
</s:form>
</div>
<hr width="40%">
<div style="text-align: center;">
	<a href="#none" id="findID">아이디 찾기</a>
	<hr>
	<a href="#none" id="findPW">패스워드 찾기</a>
</div>
<hr width="40%">
<div style="text-align: center;">
	문의 사항 
	<br>
	Phone : 010 - 5090 - 3505
	<br>
	E - mail : dandanhead@gmail.com
</div>
</body>
<script type="text/javascript">

	$("#findID").click(function() {
		var url = "findId";  
      	window.open(url, "_blank", "width = 480, height = 320, left = 200, top = 100, status = no, scrollbars = yes");
	});
	
	$("#findPW").click(function() {
		var url = "findPw";  
      	window.open(url, "_blank", "width = 480, height = 320, left = 200, top = 100, status = no, scrollbars = yes");
	});
	
</script>
</html>