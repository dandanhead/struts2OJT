<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<fmt:requestEncoding value ="utf-8"/>
<div id="top" class="layer"><img alt="no img" src="${pageContext.request.contextPath }/img/top.png" style="width: 100%;"></div>

<div id="header" class="layer">
	<div class = "topmenu" id="home" style="float: left; width: 25%;"> 
		<s:a namespace="/login" action="goMain" class="btn btn-primary">home</s:a> 
	</div>
	<div class = "topmenu" id="manageWorker" style="float: left; width: 25%;">
		<s:a namespace="/worker" action="goWorker" class="btn btn-primary">사원관리</s:a>
	</div>
	<div class = "topmenu" id="manageProject" style="float: left; width: 25%;">
		<s:a namespace="/project" action="goProject" class="btn btn-primary">프로젝트 관리</s:a>
	</div>
	<div class = "topmenu"id="mypage" style="float: left; width: 17%; width: 25%;">
		<a href="#none"  class="btn btn-primary" id="logout">로그아웃</a> 
	</div>
</div>
