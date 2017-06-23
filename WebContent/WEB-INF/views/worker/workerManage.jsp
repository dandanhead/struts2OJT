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
<jsp:include page="../login/doLogout.jsp"></jsp:include> <!--로그아웃체크  -->
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<form id="frm">
<input type="hidden" value="${wsearch}" id="whatSearch" name="wsearch">
	<div id="notice" class="layer">
		<hr>
		<div id="pagetitle" style="width: 20%;">
			<h4><b>Workers Management</b></h4>
		</div>
		<hr> <!-- 공통 css -->
		<table style="width: 100%; margin: auto;" class="table table-striped">
			<thead>
				<tr style="background-color: #aaa">
					<td>No.</td>
					<td>Name</td>
					<td>Department</td>
					<td>Phone</td>
					<td>Experience</td>
					<td>Skill Rank</td>
					<td>Position</td>
					<td>Status</td>
				</tr>
			</thead>
			<tbody>
			<colgroup>
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
				<col width="12.5%;">
			</colgroup>
				<!--리스트 뿌리기  -->
			<c:forEach var="mvo" varStatus="idx" items="${workerlist}">
				<c:if test="${empty workerlist}">
					<tr>
						<td>
							등록된 사원이 없습니다.
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty workerlist }">
					<tr>
						<td>${mvo.im_idx }</td>
						<td><a href="workerDetail.do?idx=${mvo.im_idx}&expy=${mvo.expYear}&expm=${mvo.expMonth}">${mvo.im_name }</a></td>
						<td>${mvo.im_dname }</td>
						<td>${mvo.im_phone }</td>
						<c:if test="${empty mvo.expYear or empty mvo.expMonth}">
							<td>-</td>
						</c:if>
						<c:if test="${not empty mvo.expYear and not empty mvo.expMonth }">
							<c:if test="${mvo.expYear eq 0 and mvo.expMonth eq 0 }">
								<td>신입</td>
							</c:if>
							<c:if test="${mvo.expYear eq 0 and mvo.expMonth ne 0 }">
								<td>${mvo.expMonth}개월</td>
							</c:if>
							<c:if test="${mvo.expYear ne 0 and mvo.expMonth eq 0 }">
								<td>${mvo.expYear}년</td>
							</c:if>
							<c:if test="${mvo.expYear ne 0 and mvo.expMonth ne 0 }">
								<td>${mvo.expYear}년 &nbsp;&nbsp;${mvo.expMonth}개월</td>
							</c:if>
						</c:if>
						<td>
							<c:if test="${mvo.expYear >= 0 and mvo.expYear <= 3}">
								초급 개발자
							</c:if>
							<c:if test="${mvo.expYear > 3 and mvo.expYear <= 9}">
								중급 개발자
							</c:if>
							<c:if test="${mvo.expYear > 9}">
								상급 개발자
							</c:if>
						</td>
						<td>
							<c:if test="${mvo.im_auth eq 0 }">
								Developer
							</c:if>
							<c:if test="${mvo.im_auth eq 1 }">
								Manager
							</c:if>
						</td>
						<td>
							<c:if test="${mvo.im_status eq 0}">
								대기 중
							</c:if>
							<c:if test="${mvo.im_status eq 1}">
								프로젝트 진행중
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
 		<jsp:include page="../paging/paging.jsp" flush="false">
			<jsp:param value="${pageNumber }" name="pageNumber"/>
			<jsp:param value="${pageCountPerScreen }" name="pageCountPerScreen"/>
			<jsp:param value="${recordCountPerPage }" name="recordCountPerPage"/>
			<jsp:param value="${totalRecordCount }" name="totalRecordCount"/>
		</jsp:include>
	</div>
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }"/>
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage"	value="${(empty recordCountPerPage)?0:recordCountPerPage }"/>

<div id="selectDetail" class="layer">
	<table style="width: 100%;">
		<colgroup>
			<col width="10%;">
			<col width="15%;">
			<col width="10%;">
			<col width="auto;">
		</colgroup>
		<tr>
			<td>
				<select class="form-control" name="s_category" id="category">
					<option value="im_name" <c:if test="${s_category eq 'im_name'}">selected</c:if>>Name</option>
					<option value="im_phone" <c:if test="${s_category eq 'im_phone'}">selected</c:if>>Phone</option>
					<option value="im_email" <c:if test="${s_category eq 'im_email'}">selected</c:if>>email</option>
				</select>
			</td>
			<td>
				<input type="text" class="form-control" name="s_keyword" id="keyword" value="${s_keyword}">
			</td>
			<td>
				<a class="btn btn-default" id="btnSearch">Search</a>
			</td>
			<td style="float: right;">
				<a class="btn btn-default" href="#none" id="addWorker" >add workers</a>
				
			</td>
		</tr>
	</table>
</div>
</form>
</body>
<script type="text/javascript">
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
	function goPage(pageNumber) {
		if($("#whatSearch").val() == "dep"){
			$("#_pageNumber").val(pageNumber);
	        $("#category").empty();
	        $("#keyword").empty();
			$("#frm").attr("target", "_self").attr("action", "goWorker.do").submit();
		}else if($("#whatSearch").val() == "detail"){
			$("#_pageNumber").val(pageNumber);
			$("#selectDept").empty();
			$("#frm").attr("target", "_self").attr("action", "goWorker.do").submit();
		}else{
			$("#_pageNumber").val(pageNumber);
			$("#frm").attr("target", "_self").attr("action", "goWorker.do").submit();
		}
	};
	$(document).ready(function(){
	    $('#selectDept').change(function(){
	        /* alert( $('#selectDept option:selected').val() ); */
	        $("#_pageNumber").val(0);
	        $("#category").empty();
	        $("#keyword").empty();
	        $("#whatSearch").val("dep");
	        $("#frm").attr("target", "_self").attr("action", "goWorker.do").submit();
	    });
	});
	$("#btnSearch").click(function() {
		$("#_pageNumber").val(0);
		var nullchk = $.trim($("#keyword").val());
		if(nullchk == null || nullchk == ""){
			alert("찾으실 검색어를 입력하세요");
		}else{
			$("#selectDept").empty();
			$("#whatSearch").val("detail");
			$("#frm").attr("target", "_self").attr("action", "goWorker.do").submit();
		}
	});
	$("#addWorker").click(function() {
		
		location.href="addworker.do";
	});
</script>
</html>