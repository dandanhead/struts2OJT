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
<jsp:useBean id="tips" class="kr.co.ican.help.Helps"/> <!--helps  -->
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
				<label>입사일</label> <input type="text" class="form-control" value="${tips.yymmdd(exp.ime_regi_date)}" readonly="readonly">
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
					<td style="text-align: right; vertical-align: bottom;" colspan="8"><a>더보기+</a></td>
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
			<tr>
				<td>1001</td>
				<td>홍길동</td>
				<td>SI사업부</td>
				<td>010-0000-1123</td>
				<td>3년 2개월</td>
				<td>중급 개발자</td>
				<td>Developer</td>
				<td>대기 중</td>
			</tr>
			<tr>
				<td>1001</td>
				<td>홍길동</td>
				<td>SI사업부</td>
				<td>010-0000-1123</td>
				<td>3년 2개월</td>
				<td>중급 개발자</td>
				<td>Developer</td>
				<td>대기 중</td>
			</tr>
			<tr>
				<td>1001</td>
				<td>홍길동</td>
				<td>SI사업부</td>
				<td>010-0000-1123</td>
				<td>3년 2개월</td>
				<td>중급 개발자</td>
				<td>Developer</td>
				<td>대기 중</td>
			</tr><tr>
				<td>1001</td>
				<td>홍길동</td>
				<td>SI사업부</td>
				<td>010-0000-1123</td>
				<td>3년 2개월</td>
				<td>중급 개발자</td>
				<td>Developer</td>
				<td>대기 중</td>
			</tr><tr>
				<td>1001</td>
				<td>홍길동</td>
				<td>SI사업부</td>
				<td>010-0000-1123</td>
				<td>3년 2개월</td>
				<td>중급 개발자</td>
				<td>Developer</td>
				<td>대기 중</td>
			</tr>
		</tbody>
	</table>
</div>
<div id="onproject" class="layer">
<table style="width: 100%; margin: auto;" class="table table-striped">
		<thead>
			<tr>
				<td colspan="1"><h4><b>Project Management</b></h4></td>
				<td style="text-align: right; vertical-align: bottom;" colspan="3"><a>더보기+</a></td>
			</tr>
			<tr style="background-color: #aaa">
				<td>No.</td>
				<td>title</td>
				<td>status</td>
				<td>written date</td>
			</tr>
		</thead>
		<tbody>
		<colgroup>
			<col width="20%;">
			<col width="50%;">
			<col width="20%">
		</colgroup>
			<tr>
				<td>1</td>
				<td><a>amore Project</a>&nbsp;<img alt="no img" src="img/icon_new.gif"></td>
				<td>negotiating</td>
				<td>2017.05.12</td>
			</tr>
			
			<tr>
				<td>2</td>
				<td><a>amre Project2</a>&nbsp;<img alt="no img" src="img/icon_new.gif"></td>
				<td>in progress</td>
				<td>2017.05.12</td>
			</tr>
			
			<tr>
				<td>3</td>
				<td><a>WongJin Project</a>&nbsp;<img alt="no img" src="img/icon_new.gif"></td>
				<td>in progress</td>
				<td>2017.05.12</td>
			</tr>
			
			<tr>
				<td>4</td>
				<td><a>SalesForce Project</a>&nbsp;<img alt="no img" src="img/icon_new.gif"></td>
				<td>in progress</td>
				<td>2017.05.12</td>
			</tr>
			
			<tr>
				<td>5</td>
				<td><a>HomePlus Project</a></td>
				<td>done</td>
				<td>2017.02.01</td>
			</tr>
		</tbody>
	</table>
</div>
<div id="footer" class="layer"></div>
</body>
<script type="text/javascript">
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
</script>
</html>