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
<div class="layer">
<form id="frm">
<input type="hidden" value="${expy}" name="expy"/>
<input type="hidden" value="${expm }" name="expm"/>
<input type="hidden" value="${idx}" name="idx"/> 

<!--update  -->
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Workers Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
	<div>
		<h3><b>Worker`s Info</b></h3>
	</div>
	<table style="width: 100%; margin: auto;" class="table table-bordered">
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<td>이름<input type="text" readonly="readonly" value="${mvo.im_name}" class="form-control"></td>
				<td>phone<input type="tel" readonly="readonly" value="${mvo.im_phone}" class="form-control"></td>
				<td>나이<input type="number" readonly="readonly" value="${age}" class="form-control"></td>
			</tr>
			<tr>
				<td>주민번호<input type="text" readonly="readonly" value="${mvo.im_scnum }" class="form-control"></td>
				<td>이메일<input type="email" readonly="readonly" value="${mvo.im_email }" class="form-control"></td>
				<td>경력<input type="text" readonly="readonly" value="${experience}" class="form-control"></td>
			</tr>
			<tr>
				<td>입사일<input type="text" readonly="readonly" value="${fn:substring(regiDate, 0 , 10)}" class="form-control"></td>
				<td>성별<input type="text" readonly="readonly" value="${gender}" class="form-control"></td>
				<td>집주소<input type="text" readonly="readonly" value="(${mvo.im_postcode})${mvo.im_address}&nbsp;${mvo.im_detailaddr}" class="form-control"></td>
			</tr>
			<tr>
				<td>
					부서<input type="text" readonly="readonly" value="${mvo.im_dname}" class="form-control">
				</td>
				<td>
					직급<input type="text" readonly="readonly" value="${mvo.im_auth eq 0? 'Developer' : 'Manager'}" class="form-control">
				</td>
				<td style="vertical-align: middle;">
					<a class="btn btn-default" href="#none" id="workerUpdateBtn">사원 정보 수정(Admin)</a>
					<a class="btn btn-default" href="#none" id="workerOutBtn">퇴사처리(Admin)</a>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						Available Language and Environment
					</label>
				</td>
				<td colspan="2">
					${mvo.im_skill}
				</td>
			</tr>
			<tr>
				<td>
					진행중인 프로젝트
				</td>
				<td colspan="2">
					-
				</td>
			</tr>
		</tbody>	
	</table>
	<br>
	<div>
		<h3><b>License</b></h3>
	</div>
	<table style="width: 100%; margin: auto;" class="table table-striped">
		<thead>
			<tr>
				<th>License Name</th>
				<th>Date of Acquisition</th>
				<th>Organization</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty liclist }">
				<tr>
					<td colspan="3" style="text-align: center;">
						자격증이 없습니다.
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty liclist }">
				<c:forEach items="${liclist}" var="idx" >
					<tr>
						<td>
							${idx.iml_lname }
						</td>
						<td>
							${fn:substring(idx.iml_acqdate, 0 , 10)}
						</td>
						<td>
							${idx.iml_organization}
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div>
		<h3><b>Experience</b></h3>
	</div>
	<table style="width: 100%; margin: auto;" class="table table-striped">
		<thead>
			<tr>
				<th>Service Period</th>
				<th>Working Years</th>
				<th>Company Name</th>
				<th>Position</th>
				<th>Roll</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty elist}">
				<c:forEach items="${elist}" var="idx">
					<tr>
						<td>${fn:substring(idx.ime_regi_date, 0, 10)}&nbsp;~&nbsp;${fn:substring(idx.ime_exit_date, 0, 10)}</td>
						<td>
							<fmt:parseDate value="${idx.ime_regi_date}" var="regidate" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${regidate.time / (1000*60*60*24)}" integerOnly="true" var="startDate"/>
							<fmt:parseDate value="${idx.ime_exit_date}" var="expiredate" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${expiredate.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
							
							<c:if test="${(endDate - startDate) < 0}">
								-
							</c:if>
							<c:if test="${(endDate - startDate) >= 0}">
								<c:if test="${((endDate - startDate) /365) >= 1}">
									<fmt:parseNumber value="${(endDate - startDate)/365}" var="years" integerOnly="true"/>
									<fmt:parseNumber value="${((endDate - startDate) % 365)/30}" var="months" integerOnly="true"/>
									<c:if test="${months eq 0}">
										${years}년	
									</c:if>
									<c:if test="${months ne 0}">
										${years}년  ${months}개월	
									</c:if>
									 
								</c:if>
								<c:if test="${((endDate - startDate) /365) < 1}">
									<fmt:parseNumber value="${((endDate - startDate) % 365)/30}" var="months" integerOnly="true"/>
									${months}개월
								</c:if> 							
							</c:if>
						</td>
						<td>${idx.ime_coname }</td>
						<td>${idx.ime_auth eq 0? 'Developer' : 'Manager' }</td>
						<td>${idx.ime_roll}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty elist}">
				<tr>
					<td colspan="5" style="text-align: center;">
						경력 사항이 없습니다.
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
 		<jsp:include page="../paging/paging.jsp" flush="false">
			<jsp:param value="${pageNumber }" name="pageNumber"/>
			<jsp:param value="${pageCountPerScreen }" name="pageCountPerScreen"/>
			<jsp:param value="${recordCountPerPage }" name="recordCountPerPage"/>
			<jsp:param value="${totalRecordCount }" name="totalRecordCount"/>
		</jsp:include>
	
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }"/>
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage"	value="${(empty recordCountPerPage)?0:recordCountPerPage }"/>
	<div>
		<h3><b>Project History</b></h3>
	</div>
	<table style="width: 100%; margin: auto;" class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>Project Name</th>
				<th>Client</th>
				<th>End Date</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td><a>amore Project</a></td>
				<td>amore</td>
				<td>2017.05.12</td>
			</tr>
			</tbody>
	</table>
	<nav style="text-align: center;">
		  <ul class="pagination">
		    <li>
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <li><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">6</a></li>
		    <li><a href="#">7</a></li>
		    <li><a href="#">8</a></li>
		    <li><a href="#">9</a></li>
		    <li><a href="#">10</a></li>
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
</form>
</div>
<div id="selectDetail" class="layer">
	<table style="width: 100%;">
		<colgroup>
			<col width="10%;">
			<col width="15%;">
			<col width="auto;">
		</colgroup>
		<tr>
			<td>
				<select class="form-control">
					<option selected="selected">Project Name</option>
					<option>End Date</option>
					<option>Client</option>
				</select>
			</td>
			<td>
				<input type="text" class="form-control">
			</td>
			<td style="float: left;">
				<a class="btn btn-default">Search</a>
			</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
	
	$("#workerUpdateBtn").click(function() {
		
		if(confirm("수정 하시겠습니까?")){
			
			$("#frm").attr({"target" : "_self" , "action" : "workerUpdate.do"}).submit();	
		}
	});
	
	$("#workerOutBtn").click(function() {
		if(confirm("퇴사 처리 하시겠습니까?")){
			$("#frm").attr({"target" : "_self" , "action" : "workerResign.do"}).submit();
		}
	});
	function goPage(pageNumber) {
		$("#_pageNumber").val(pageNumber);
		$("#frm").attr("target", "_self").attr("action", "workerDetail.do").submit();
	};
</script>
</html>