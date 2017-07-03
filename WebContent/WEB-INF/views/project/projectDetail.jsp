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
<style type="text/css">
.popover-title{
    background: #ffff99;
}
</style>
</head>
<body>
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<div id="notice" class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Project Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
</div>
<div class="layer">
	<h3><strong>Project Information</strong></h3>
	<table style="width: 100%; margin: auto;" class="table table-bordered">
 		<colgroup>
			<col width="33%">
			<col width="33%">
			<col width="33%">
		</colgroup>
		<tr>
			<td>ProjectName<input type="text" readonly="readonly" value="${pvo.ipl_pname}" class="form-control"></td>
			<td>Client<input type="text" readonly="readonly" value="${pvo.ipl_client}" class="form-control"></td>
			<td>Language AND Environment<input type="text" readonly="readonly" value="${pvo.ipl_skill }" class="form-control"></td>
		</tr>
		<tr> 
		
			<td>
			Content
			<a tabindex="0" class="btn btn-default" role="button" data-toggle="popover" data-trigger="focus" title="Details" 
			data-content="${pvo.ipl_content }">상세 내용 확인하기</a>
			</td>
			<td>Location<input type="text" readonly="readonly" value="(${pvo.ipl_postcode})${pvo.ipl_address}${pvo.ipl_detailaddr}" class="form-control"></td>
			<td>Charge<input type="text" readonly="readonly" value="${pvo.ipl_charge}" class="form-control"></td>
		</tr>
		<tr>
			<td>Start Date<input type="text" readonly="readonly" value="${fn:substring(pvo.ipl_sdate, 0 , 10)}" class="form-control"></td>
			<td>Expect End Date<input type="text" readonly="readonly" value="${fn:substring(pvo.ipl_eptdate, 0 , 10)}" class="form-control"></td>
			<td>End Date<input type="text" readonly="readonly" value="${fn:substring(pvo.ipl_edate, 0 , 10)}" class="form-control"></td>
		</tr>
		<tr>
			<td>
				Document
				<br>
				<%-- <c:if test="${empty pvo.ipl_doc }">
					<input type="text" value="첨부 파일이 없습니다." disabled="disabled" class="form-control"> 
				</c:if>
				<c:if test="${not empty pvo.ipl_doc }">
					<button type="button" class="btn btn-default" aria-label="Left Align" onclick="fileDowns('${pvo.ipl_idx}','${pvo.ipl_doc}')" style="width: 100%;">
						 <span class="glyphicon glyphicon-download-alt" aria-hidden="true">${pvo.ipl_doc}</span>
					</button>
				</c:if> --%>
				<a href="fileDownload?fileName=${pvo.ipl_doc}">${pvo.ipl_doc}</a>
			</td>
			<td style="vertical-align: bottom;">
				<a href="#none" class="btn btn-default">Fix</a>
			</td>
			<td style="vertical-align: bottom;">
				<a href="goProject" class="btn btn-default">Return</a>	
			</td>
		</tr>
	</table>
	<br>
	<h3><strong>Join Member List</strong></h3>
	<table style="width: 100%; margin: auto;" class="table table-bordered">
		<colgroup>
			<col width="10%;">
			<col width="18%;">
			<col width="18%;">
			<col width="18%;">
			<col width="18%;">
			<col width="18%;">
		</colgroup>
		<thead>
			<tr>
				<th>No</th>
				<th>Department</th>
				<th>Name</th>
				<th>Career</th>
				<th>Phone</th>
				<th>E-Mail</th>
			</tr>
		</thead>
		<tbody>
		<!-- 프로젝트 참여 인원 뿌리기 list -->
		</tbody>
	</table>
	<span style="float: right;">
		<a class="btn btn-default" href="#none" style="width: 100%;" id="assignMember">Assign</a>
	</span>
</div>
<form name="filedownForm" action="filedown" method="post">
	<input type="hidden" name="filename"  value=""/>
	<input type="hidden" name="idx"  value=""/>
</form>
<form id="popfrm" method="post">
	<input type="hidden" value="${pvo.ipl_idx}" name="ipl_idx" id="popidx">
</form>
</body>
<script type="text/javascript">
	$(".delmem").click(function() {
		if(confirm("해당 member를 제외 하시겠습니까?")){
			alert("해당 member 삭제!");
		}
	});
	
	$("#assignMember").click(function() {
      	window.open("", "popupform", "width = 1024, height = 860, left = 200, top = 100, status = no, scrollbars = yes");
      	
      	$("#popfrm").attr({"target" : "popupform" , "action" : "assignMember"}).submit();
	});
	
	//logout
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href='<s:url action="logout" namespace="/login"/>';
			}
	});
	
	function fileDowns(idx, filename) {
		
		var doc = document.filedownForm;
		doc.filename.value = filename;
		doc.idx.value = idx;
		doc.submit();
	}
	
	$(function () {
	    $('[data-toggle="popover"]').popover()
    });
</script>
</html>