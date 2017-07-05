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
<title>Insert title here</title>
</head>
<body>
<s:form style="width: 80%; margin: auto;" action="addMemberToProject" name="/project" id="frm">
<div>
	<h3>Assign Member</h3>
	<hr>
	<select>
		<option>전체</option>
		<option>잉여인력</option>
		<option>투입중 인력</option>
		<option>스킬</option>
	</select>
	<input type="text" class="form-contrl"/>
	<button id="assignbtn" class="btn btn-warning" style="float: right;">Add Members!</button>
	<table style="width: 100%; margin: auto;" class="table table-bordered">
		<colgroup>
			<col width="10%;">
			<col width="15%;">
			<col width="15%;">
			<col width="35%;">
			<col width="20%;">
			<col width="5%;">
		</colgroup>
		<thead>
			<tr>
				<th>No</th>
				<th>Name</th>
				<th>Career</th>
				<th colspan="3">Skill</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty aslist}">
				<tr>
					<td colspan="6">가용 인력이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty aslist }">
				<c:forEach items="${aslist}" var="idx">
						<tr>
							<td>${idx.im_idx}</td>
							<td>${idx.im_name}</td>
							<c:if test="${empty idx.expYear or empty idx.expMonth}">
								<td>-</td>
							</c:if>
							<c:if test="${not empty idx.expYear and not empty idx.expMonth }">
								<c:if test="${idx.expYear eq 0 and idx.expMonth eq 0 }">
									<td>신입</td>
								</c:if>
								<c:if test="${idx.expYear eq 0 and idx.expMonth ne 0 }">
									<td>${idx.expMonth}개월</td>
								</c:if>
								<c:if test="${idx.expYear ne 0 and idx.expMonth eq 0 }">
									<td>${idx.expYear}년</td>
								</c:if>
								<c:if test="${idx.expYear ne 0 and idx.expMonth ne 0 }">
									<td>${idx.expYear}년 &nbsp;&nbsp;${idx.expMonth}개월</td>
								</c:if>
							</c:if>
							<td>${idx.im_skill}</td>
							<td>
								<select class="form-control" style="width: 80%;" name="rolls">
									<option value="0">Developer</option>
									<option value="1">Manager</option>
								</select>
							</td>
							<td><input type="checkbox" class="form-control" name="chkvalues" value="${idx.im_idx}"></td>
						</tr>
					</c:forEach>
			</c:if>
			<tr>
				<td colspan="6">
					<button type="button" style="width: 100%;" id="readmore">Read More</button>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="insertCheck" id="inschk" value="${insertCheck}">
	<input type="hidden" name="ipl_idx" value="${ipl_idx}" id="chkchk">
</div>
</s:form>
</body>
<script type="text/javascript">
$("#assignbtn").click(function() {
	if(confirm("등록 하시겠습니까?")){
		$("#frm").submit();
	}
});

$(function() {
	var val = $("#inschk").val();
	if(val > 0 ){
		opener.parent.location.reload();
		window.close();
	}
	
});
/* 	$("#readmore").click(function() {
		var pagenumber = $("#pageNumber").val();
		$.ajax({
			url : 'getjsonmemlist',
			type : "POST",
			cache : false,
			dataType : "json",
			data : { "asvo.pageNumber" : pagenumber },
			success : function(data) {
				alert(data);
			},
			error : function(request, status, error) {
				alert(status);
				alert(error);
			}
		});
	}); */
</script>
</html>