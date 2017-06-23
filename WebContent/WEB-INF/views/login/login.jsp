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
div.form-group{
	width: 50%;
	text-align: center;
}
</style>
</head>
<body>
<br><br><br><br><br><br><br>
<div style="width: 720px; height: 480px; background-image: url('img/icanback.png'); margin: auto;">
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<form class="form-horizontal" style="margin-left: 200px;" id="loginFrm" method="post">
  <div class="form-group">
    <label for="inputid" class="col-sm-2 control-label" style="color: white;">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputid" placeholder="input your ID" name="im_idx">
    </div>
  </div>
  <div class="form-group">
    <label for="inputpw" class="col-sm-2 control-label" style="color: white;">PW</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputpw" placeholder="input your Password" name="im_pw">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-default" style="width: 100%;" id="btnLogin">Login</button>
    </div>
  </div>
</form>
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
		var url = "goFindID.do";  
      	window.open(url, "_blank", "width = 480, height = 320, left = 200, top = 100, status = no, scrollbars = yes");
	});
	$("#findPW").click(function() {
		var url = "goFindPW.do";  
      	window.open(url, "_blank", "width = 480, height = 320, left = 200, top = 100, status = no, scrollbars = yes");
	});
	$("#btnLogin").click(function() {
		$("#loginFrm").attr({"target" : "_self" , "action" : "login.do"}).submit();
	});
</script>
</html>