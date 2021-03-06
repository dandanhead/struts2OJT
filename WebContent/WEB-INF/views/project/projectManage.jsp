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
<jsp:include page="../layout/header.jsp"/> <!--header  -->>
<s:form id="frm" style="width:80%; margin:auto;" namespace="/">
<div class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Project Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
<table style="width: 100%; margin: auto;" class="table table-striped">
			<colgroup>
				<col width="10%;">
				<col width="30%;">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr style="background-color: #aaa">
					<td>No.</td>
					<td>Project Name(sorting▼)</td>
					<td>Client</td>
					<td>Start Date(sorting▼)</td>
					<td>Expect End Date(sorting▼)</td>
					<td>End Date(sorting▼)</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty plist}" >
					<tr>
						<td colspan="5" style="text-align: center;">
							생성된 프로젝트가 존재 하지 않습니다. 프로젝트를 생성해 주세요.
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty plist}" >
					<c:forEach items="${plist }" var="idx">
						<tr>
							<td>
								${idx.ipl_idx}
							</td>
							<td>
								<a href="projectDetail?ipl_idx=${idx.ipl_idx}">${idx.ipl_pname}</a>
							</td>
							<td>
								${idx.ipl_client}
							</td>
							<td>
								${fn:substring(idx.ipl_sdate, 0 , 10) }
							</td>
							<td>
								<c:if test="${empty idx.ipl_eptdate}">
									-
								</c:if>
								<c:if test="${not empty idx.ipl_eptdate}">
									${fn:substring(idx.ipl_eptdate, 0 , 10) }
								</c:if>
							</td>
							<td>
								<c:if test="${empty idx.ipl_edate}">
									-
								</c:if>
								<c:if test="${not empty idx.ipl_edate}">
									${fn:substring(idx.ipl_edate, 0 , 10) }
								</c:if>
								
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
 		<jsp:include page="../paging/paging.jsp" flush="false">
			<jsp:param value="${pvo.pageNumber }" name="pageNumber"/>
			<jsp:param value="${pageCountPerScreen }" name="pageCountPerScreen"/>
			<jsp:param value="${pvo.recordCountPerPage }" name="recordCountPerPage"/>
			<jsp:param value="${totalRecordCount }" name="totalRecordCount"/>
		</jsp:include>
	</div>
	<input type="hidden" name="pvo.pageNumber" id="_pageNumber" value="${(empty pvo.pageNumber)?0:pvo.pageNumber }"/>
<input type="hidden" name="pvo.recordCountPerPage" id="_recordCountPerPage"	value="${(empty pvo.recordCountPerPage)?0:pvo.recordCountPerPage }"/>
</s:form>
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
				<select class="form-control">
					<option selected="selected">Project Name</option>
					<option>Client</option>
				</select>
			</td>
			<td>
				<input type="text" class="form-control">
			</td>
			<td>
				<a class="btn btn-default">Search</a>
			</td>
			<td style="float: right;">
				<a class="btn btn-default" href="addProject" >add Project</a>
			</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	$("#logout").click(function() {
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href='<s:url action="logout" namespace="/login"/>';
		}
	});
	
	function goPage(pageNumber) {
		
		$("#_pageNumber").val(pageNumber);
		$("#frm").attr("target", "_self").attr("action", "goProject").submit();
	};
</script>
</html>