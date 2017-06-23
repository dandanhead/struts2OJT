<%@page import="kr.co.ican.vo.ExperienceVO"%>
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
<!-- Daum Adress API Lib  -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"/> <!--header  -->
	<div id="notice" class="layer">
		<hr>
		<div id="pagetitle" style="width: 20%;">
			<h4><b>Workers Management</b></h4>
		</div>
		<hr> <!-- 공통 css -->
	</div>
<form id="frm" method="post">
	<input type="hidden" id="chkmailDupl" value="${mvo.im_email}">
	<input type="hidden" id="chkphoneDupl" value="${mvo.im_phone}">
	<!-- change flag -->
	<input type="hidden" id="licFlag" value="0" name="licflag">
	<input type="hidden" id="skillFlag" value="0" name="skillflag">
	<input type="hidden" id="expFlag" value="0" name="expflag">
	<!--사번  -->
	<input type="hidden" name="im_idx" value="${mvo.im_idx}">
	<!--받아온 경력 size  -->
	<input type="hidden" id="expsize" value="${fn:length(elist)}">
	<input type="hidden" id="licsize" value="${fn:length(liclist)}">
	<div class="layer" style="margin-left: 30%;">
		<div class="addform" style="float: left;">
		<h2><b>Information</b></h2>
			<div>
				<label>PW</label>
				<input type="password" class="form-control" style="width: 40%;" name="im_pw" id="chkpw" value="${mvo.im_pw}">
			</div>
			<div>
				<label>PW(Confirm)</label>
				<input type="password" class="form-control" style="width: 40%;" id="confirmpw" value="${mvo.im_pw}">
			</div>
			<div>
				<label>Name</label>
				<input type="text" class="form-control" style="width: 40%;" name="im_name" id="chkname" readonly="readonly" value="${mvo.im_name}">
			</div>
			<div>
				<label>Social Num</label>
				<c:forTokens items="${mvo.im_scnum}" delims="-" var="idx" varStatus="vs">
					<c:if test="${vs.count eq 1 }">
						<c:set value="${idx}" var="startnum"/>
					</c:if>
					<c:if test="${vs.count eq 2 }">
						<c:set value="${idx}" var="endnum"/>
					</c:if>
				</c:forTokens>
				<div class="row">
				  <div class="col-xs-3">
				    <input type="text" class="form-control" placeholder="앞 자리" id="snumF" value="${startnum}" readonly="readonly">
				  </div>
				  <div class="col-xs-3">
				    <input type="password" class="form-control" placeholder="뒷 자리" id="snumE" value="${endnum}" readonly="readonly">
				  </div>
				  <input type="hidden" value="" name="im_scnum" id="chkscnum">
				</div>
			</div>
			<div>
				<label>Phone</label>
				<div class="row">
				  <div class="col-xs-2">
				  	<c:forTokens items="${mvo.im_phone }" delims="-" var="idx" varStatus="vs">
				  		<c:if test="${vs.count eq 1}">
				  			<c:set value="${idx}" var="sphone"/>
				  		</c:if>
				  		<c:if test="${vs.count eq 2}">
				  			<c:set value="${idx}" var="mphone"/>
				  		</c:if>
				  		<c:if test="${vs.count eq 3}">
				  			<c:set value="${idx}" var="ephone"/>
				  		</c:if>
				  	</c:forTokens>
				    <input type="text" class="form-control" placeholder="010" id="sphone" style="width: 80%" value="${sphone}">
				  </div>
				  <div class="col-xs-2">
				    <input type="text" class="form-control" id="mphone" style="width: 80%" value="${mphone}">
				  </div>
				  <div class="col-xs-2">
				    <input type="text" class="form-control" id="ephone" style="width: 80%" value="${ephone}">
				  </div>
				  <input type="hidden" value="" name="im_phone" id="chkphone">
				</div>
			</div>
			<div>
				<label>E-mail</label>
				<input type="email" class="form-control" name="im_email" id="chkemail" style="width: 50%" value="${mvo.im_email}">
			</div>
			<div>
				<label>Address</label>
				<br>
				<input type="text" id="postcode" style="width: 30%" placeholder="우편번호" name="im_postcode" readonly="readonly" value="${mvo.im_postcode}">
				<a href="#none" class="btn btn-default" style="width: 20%" id="findaddr">우편번호찾기</a>
				<br>
				<input type="text"  class="form-control" id="tempaddress" style="width: 50%" placeholder="도로명 또는 지번 주소" readonly="readonly" value="${mvo.im_address}" name="im_address">
				<input type="text"  class="form-control" id="detailaddress" style="width: 50%" placeholder="상세 주소" value="${mvo.im_detailaddr}" name="im_detailaddr">
			</div>
			<div class="chkbox">
				<label>Language</label>
				<input type="text" name="im_skill" class="form-control" style="width: 50%; text-transform: uppercase; ime-mode: disabled;" id="skillinput" value="${mvo.im_skill}">
			</div>
			<div class="licensechkbox">
				<label>License</label>
				<a class="btn btn-default" style="width: 30%; float: right;" id="addLicense">Add License</a>
				<div>
					<table class="table table-striped" id="ltb">
						<colgroup>
							<col width="30%">
							<col width="30%">
							<col width="30%">
							<col width="10%">
						</colgroup>
						<tr>
							<th>자격증 명</th>
							<th>취득일</th>
							<th colspan="2">발급기관</th>
						</tr>
						<c:forEach items="${liclist}" var="idx">
				 		<tr>
						    <td><input class="form-control" type="text" style="width: 100%;" name="iml_lname" value="${idx.iml_lname}"></td>
						    <td><input class="form-control" type="text" style="width: 100%;" name="iml_acudate" readonly="readonly" value="${fn:substring(idx.iml_acqdate, 0 , 10)}"></td>
						    <td><input class="form-control" type="text" style="width: 100%;" name="iml_organization" value="${idx.iml_organization}"></td>
						    <td><a class="btn btn-default">cancle</a></td>
	  					 </tr> 
						</c:forEach>
					</table>
				</div>
			</div>
			<div id="selectDep">
				<label>Department</label>
				<select class="form-control" style="width: 30%;" name="im_dname">
					<option value="대표이사" <c:if test="${mvo.im_dname eq '대표이사'}">selected</c:if>>대표이사</option>
					<option value="전무이사" <c:if test="${mvo.im_dname eq '전무이사'}">selected</c:if>>전무이사</option>
					<option value="상무이사" <c:if test="${mvo.im_dname eq '상무이사'}">selected</c:if>>상무이사</option>
					<option value="SO사업부" <c:if test="${mvo.im_dname eq 'SO사업부'}">selected</c:if>>SO사업부</option>
					<option value="SI사업부" <c:if test="${mvo.im_dname eq 'SI사업부'}">selected</c:if>>SI사업부</option>
					<option value="타업체인력"<c:if test="${mvo.im_dname eq '타업체인력'}">selected</c:if> >타업체인력</option>
					<option value="FreeLancer"<c:if test="${mvo.im_dname eq 'FreeLancer'}">selected</c:if> >FreeLancer</option>
					<option value="관리부" <c:if test="${mvo.im_dname eq '관리부'}">selected</c:if>>관리부</option>
				</select>
				<label id="conamelabel">※타업체 인력인 경우 소속 회사를 입력해야 합니다.</label>
				<input type="text" class="form-control" style="width: 30%" name="outsideperson" id="coname">
				
			</div>
			<div>
				<label>Authority</label>
				<select class="form-control" style="width: 30%;" name="im_auth">
						<option value="0" <c:if test="${mvo.im_auth eq 0}">selected</c:if>>Developer</option>
						<option value="1" <c:if test="${mvo.im_auth eq 1}">selected</c:if>>Manager</option>
				</select>
			</div>
			<br>
			<hr>
			<h2><b>Experience</b></h2>
			<!-- 경력 보여주기  -->
			<a class="btn btn-default" style="width: 30%; float: right;" id="addCareers">Add Careers</a>
				<div>
					<table class="table table-striped" id="ctb">
						<colgroup>
							<col width="13%">
							<col width="13%">
							<col width="20%">
							<col width="20%">
							<col width="24%">
							<col width="10%">
						</colgroup>
						<tr>
							<th colspan="2">근무기간</th>
							<th>회사명</th>
							<th>직위</th>
							<th colspan="2">역할</th>
						</tr>
						<c:forEach items="${elist}" var="iddx">
							<tr class="trappend">
								<td><input class="form-control" type="text" style="width: 100%;" name="ime_regi_date" readonly="readonly" value="${iddx.ime_regi_date}"></td>
								<td><input class="form-control" type="text" style="width: 100%;" name="ime_exit_date" readonly="readonly" value="${iddx.ime_exit_date}"></td>
								<td><input class="form-control" type="text" style="width: 100%;" name="ime_coname" value="${iddx.ime_coname}"></td>
								<td>
									<select class="form-control" style="width: 80%;" name="ime_auth">
									<option value="0" <c:if test="${iddx.ime_auth eq 0}">selected</c:if>>Developer</option>
									<option value="1" <c:if test="${iddx.ime_auth eq 1}">selected</c:if>>Manager</option>
									</select>  
								</td>
								<td><input class="form-control" type="text" style="width: 100%;" name="ime_roll" value="${iddx.ime_roll }"></td>
								<td><a class="btn btn-default">cancle</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			<div >
				<a class="btn btn-default" href="#none" style="width: 40%;" id="addbtn">Submit</a>
				<a class="btn btn-default" href="goWorker.do" style="width: 40%;" id="chkchk">Return</a>
			</div>
		</div>
	</div>
</form>
</body>
<script>
$("#findaddr").click(function() {
	new daum.Postcode({
        oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('tempaddress').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('detailaddress').focus();
        }
    }).open();
});
//대문자 영어 대문자
$("#skillinput").keyup(function() {
	$(this).val($(this).val().toUpperCase());
	$(this).val($(this).val().replace(/[\{\}\[\]\/?.;:|\)*~`!^\-_<>@\$%&\\\=\(\'\"]|[가-힣]|[ㄱ-ㅎ]|[ㅏ-ㅣ]|[0-9]/gi,""));
});
</script>
<script type="text/javascript" src="js/updateworkerjQuery.js?version=20170611232354341"></script>
</html>