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
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<jsp:useBean id="tips" class="kr.co.ican.util.Helps"/> <!--helps  -->
<hr>
<div id="info" class="layer" style="text-align: center;">
		<form class="form-inline">
			<div class="form-group">
				<label>사번</label> <input type="text" class="form-control" value="${login.im_idx}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>직급</label> <input type="text" class="form-control" value="${tips.selectAuthority(login.im_auth)}" readonly="readonly">
				
			</div>	
			<div class="form-group">
				<label>이름</label> <input type="text" class="form-control" value="${login.im_name }" readonly="readonly">
			</div>
			<div class="form-group">
				<label>입사일</label> <input type="text" class="form-control" value="${tips.yymmdd(regidate)}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>연락처</label> <input type="tel" class="form-control" value="${login.im_phone}" readonly="readonly">
			</div>			
		</form>
</div>
<hr>
<div id="notice" class="layer">
	<table style="width: 100%; margin: auto;" class="table table-striped">
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
		<thead>
				<tr>
					<td colspan="3"><h4><b>Worker Management</b></h4></td>
					<td style="text-align: right; vertical-align: bottom;" colspan="8">
						<s:a namespace="/worker" action="goWorker">더보기+</s:a>
					</td>
				</tr>
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
			<!-- 사원 리스트 뿌리기 -->
			<c:if test="${empty workerlist}">
				<tr>
					<td colspan="9" style="text-align: center;"> 등록된 사원이 없습니다. </td>
				</tr>
			</c:if>
			<c:if test="${not empty workerlist}">
				<c:forEach items="${workerlist}" var="mvo">
					<tr>
						<td>${mvo.im_idx }</td>
						<td>${mvo.im_name }</td>
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
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<br>
<div id="onproject" class="layer">
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
			<tr>
				<td colspan="1"><h4><b>Project Management</b></h4></td>
				<td style="text-align: right; vertical-align: bottom;" colspan="5">
					<s:a namespace="/project" action="goProject">더보기+</s:a>
				</td>
			</tr>
			<tr style="background-color: #aaa">
					<td>No.</td>
					<td>Project Name</td>
					<td>Client</td>
					<td>Start Date</td>
					<td>Expect End Date</td>
					<td>End Date</td>
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
								${idx.ipl_pname}
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
</div>
<div id="footer" class="layer"></div>
</body>
<script type="text/javascript">
$("#logout").click(function() { //로그아웃 클릭
	if(confirm("로그 아웃 하시겠습니까?")){
		location.href='<s:url action="logout" namespace="/login"/>';
	}
});
</script>
</html>