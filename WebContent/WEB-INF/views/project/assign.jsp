<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value ="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/> 
<title>Insert title here</title>
</head>
<body>
<div id="top" class="layer"><img alt="no img" src="img/top.png" style="width: 100%;">
</div>
<form id="frm">
	<div id="notice" class="layer">
		<hr>
		<div id="pagetitle" style="width: 20%;">
			<h4><b>Workers Management</b></h4>
		</div>
		<hr> <!-- 공통 css -->
		<table style="width: 100%; margin: auto;" class="table table-striped">
			<colgroup>
				<col width="10%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
			</colgroup>
			<thead>
				<tr style="background-color: #aaa">
					<td>Department</td>
					<td>Name</td>
					<td>Experience</td>
					<td>SkillRank</td>
					<td>Status</td>
					<td>Roll</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<!-- 사원 리스트  -->
				<tr>
					<td colspan="7">
						투입 중인 사원이 없습니다. 추가 버튼을 눌러 인원을 추가 해 주세요.
					</td>
				</tr>
			</tbody>
		</table>
		<button type="button" id="addhtmlbtn" class="btn btn-default">상세검색 보기</button>
		<div id="detailSearch" hidden="true">
				<select class="form-control" style="width: 15%; float: left;">
					<option selected="selected">전체인원</option>
					<option>가용인원</option>
					<option>투입 중인 인원</option>
				</select>&nbsp;&nbsp;&nbsp;
				<select class="form-control" style="width: 15%; float: left;">
					<option selected="selected" class="form-control">경력</option>
					<option>3년 미만</option>
					<option>5년 미만</option>
					<option>10년 미만</option>
					<option>10년 이상</option>
				</select>&nbsp;&nbsp;&nbsp;
				<input type="text" class="form-control" placeholder="skill" style="width: 40%; float: left;">
				<a href="#none" id="detailsrchBtn" class="btn btn-default" style="width: 10%; float: left;">Search</a>
		</div>
		<!-- 페이징  -->
	</div>
</form>
<div id="selectDetail" class="layer">
	<br>
	<div class="layer" style="float: left;">
		<label>Added Member</label>
		<br>
		<p>
			<a>육지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a>칠지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>팔지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>구지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>십지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;	
		</p>
		<div>
			<a href="#none" class="btn btn-default" style="width: 30%;" id="addMembers">추가 하기</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$("#addMembers").click(function() {
		
		opener.parent.location.reload();
		window.close();
	});
	
	$("#addhtmlbtn").click(function() {
		
		$(this).text("상세검색 숨기기");
		
		$("#detailSearch").show();
	});
</script>
</html>