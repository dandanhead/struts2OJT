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
<s:form id="frm" method="post" action="addworkerAf" namespace="/worker">
	<div class="layer" style="margin-left: 30%;">
		<div class="addform" style="float: left;">
		<h2><b>Information</b></h2>
		<h5><strong style="color: red;">* 항목은 필수 입력 입니다.</strong></h5>
			<div>
				<label>* PW</label>
				<br>
				<input type="password" class="form-control" style="width: 40%;" name="mvo.im_pw" id="chkpw" value="${mvo.im_pw}">
				<b style="color: red;"><s:fielderror fieldName="chkpw"/></b>
			</div>
			<br>
			<div>
				<label>* PW(Confirm)</label>
				<input type="password" class="form-control" style="width: 40%;" id="confirmpw" name="confirmPw" value="${confirmPw}">
				<b style="color: red;"><s:fielderror fieldName="chkpwconfirm"/></b>
			</div>
			<br>
			<div>
				<label>* Name</label>
				<input type="text" class="form-control" style="width: 40%;" name="mvo.im_name" id="chkname" value="${mvo.im_name}">
				<b style="color: red;"><s:fielderror fieldName="chkname"/></b>
			</div>
			<br>
			<div>
				<label>* Social Num</label>
				<div class="row">
				  <div class="col-xs-3">
				    <input type="text" class="form-control" placeholder="앞 자리" name="snumF" value="${snumF}" maxlength="6">
				  </div>
				  <div class="col-xs-3">
				    <input type="password" class="form-control" placeholder="뒷 자리" name="snumE" value="${snumE}" maxlength="7">
				  </div>
				</div>
				<b style="color: red;"><s:fielderror fieldName="chkscnum"/></b>
			</div>
			<br>
			<div>
				<label>* Phone</label>
				<div class="row">
				  <div class="col-xs-2">
				    <input type="text" class="form-control" placeholder="010" name="sphone" style="width: 80%" value="${sphone}" maxlength="3">
				  </div>
				  <div class="col-xs-2">
				    <input type="text" class="form-control" name="mphone" style="width: 80%" value="${mphone}" maxlength="4">
				  </div>
				  <div class="col-xs-2">
				    <input type="text" class="form-control" name="ephone" style="width: 80%" value="${ephone}" maxlength="4">
				  </div>
				</div>
				<b style="color: red;"><s:fielderror fieldName="chkphone"/></b>
			</div>
			<br>
			<div>
				<label>* E-mail</label>
				<input type="email" class="form-control" name="mvo.im_email" id="chkemail" style="width: 50%" value="${mvo.im_email}">
				 <b style="color: red;"><s:fielderror fieldName="chkemail"/></b>
			</div>
			<br>
			<div>
				<label>* Address</label>
				<br>
				<input type="text" id="postcode" style="width: 30%" placeholder="우편번호" name="mvo.im_postcode" readonly="readonly" value="${ mvo.im_postcode}">
				<a href="#none" class="btn btn-default" style="width: 20%" id="findaddr">우편번호찾기</a>
				<br>
				<input type="text"  class="form-control" id="tempaddress" style="width: 50%" placeholder="도로명 또는 지번 주소" readonly="readonly" name="mvo.im_address" value="${mvo.im_address}">
				<input type="text"  class="form-control" id="detailaddress" style="width: 50%" placeholder="상세 주소" name="mvo.im_detailaddr" value="${mvo.im_detailaddr}">
			</div>
			<b style="color: red;"><s:fielderror fieldName="chkaddress"/></b>
			<br>
			<div class="chkbox">
				<label>* Language and Skill</label>
				<div style="color: red;">사용가능 언어 및 환경은 영어로만 입력 하실 수 있으며 comma(,) 로 구분합니다.</div>
				<br>
				<input type="text" name="mvo.im_skill" class="form-control" style="width: 50%; text-transform: uppercase; ime-mode: disabled;" id="skillinput"  value="${mvo.im_skill}">
				<b style="color: red;"><s:fielderror fieldName="chkskill"/></b>
			</div>
			<br>
			<div id="selectDep">
				<label>* Department</label>
				<select class="form-control" style="width: 30%;" name="mvo.im_dname">
					<option value="대표이사">대표이사</option>
					<option value="전무이사">전무이사</option>
					<option value="상무이사">상무이사</option>
					<option value="SO사업부">SO사업부</option>
					<option value="SI사업부">SI사업부</option>
					<option value="타업체인력">타업체인력</option>
					<option value="FreeLancer">FreeLancer</option>
					<option value="관리부">관리부</option>
				</select>
				<label id="conamelabel" hidden="true">※타업체 인력인 경우 소속 회사를 입력해야 합니다.</label>
				<input type="text" class="form-control" style="width: 30%" name="outsideperson" id="coname" hidden="true" placeholder="회사명을 입력 해 주세요.">
				 <b style="color: red;"><s:fielderror fieldName="chkTaCoName"/></b>
			</div>
			<br>
			<div>
				<label>* Authority</label>
				<select class="form-control" style="width: 30%;" name="mvo.im_auth">
						<option selected="selected" value="0">Developer</option>
						<option value="1">Manager</option>
				</select>
			</div>
			<br>
			<hr>
			<h2><b>License</b></h2>
			<a class="btn btn-default" style="width: 20%; float: right; margin-right: 17%;" id="addLicense">Add License</a>
			<b style="color: red;"><s:fielderror fieldName="chklicense"/></b>
			<table class="table table-striped" id="ltb" style="width: 40%; margin: auto;">
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
			</table>
			<hr>
			<div style="width: 40%; margin: auto;'" >
			<h2><b>Experience</b></h2>
			<a class="btn btn-default" style="width: 24%; float: right;" id="addCareers">Add Careers</a>
			<b style="color: red;"><s:fielderror fieldName="chkcareer"/></b>
			<table class="table table-striped" id="ctb">
					<colgroup>
						<col width="18%">
						<col width="18%">
						<col width="18%">
						<col width="18%">
						<col width="18%">
						<col width="10%">
					</colgroup>
					<tr>
						<th colspan="2">근무기간</th>
						<th>회사명</th>
						<th>직위</th>
						<th colspan="2">역할</th>
					</tr>
			</table>
			</div>
			
			<br>
			<hr>
			<div style="width: 40%; margin: auto;">
				<button type="button" class="btn btn-default" style="width: 40%;" id="submitbtn">Submit</button>
				<a class="btn btn-default" href="goWorker.do" style="width: 40%;" id="chkchk">Return</a>
			</div>
		</div>
	</div>
	<!--hidden values  -->
	<input type="hidden" name="chkTa" id="cntChkTa" value="0">
	<input type="hidden" name="chkLicense" id="licenseCount" value="0">
	<input type="hidden" name="chkCareer" id="careerCount" value="0">
	<input type="hidden" name="errMessage" id="errmsg" value="${errMessage}">
</s:form>
</body>
<script>
//주소 API
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
$("#submitbtn").click(function() {
	if(confirm("등록 하시겠습니까?")){
		$("#frm").submit();
	}
});
//스킬 입력시 영어 대문자만 허용,콤마로 구분 , skillinput
$("#skillinput").keyup(function() {
	$(this).val($(this).val().toUpperCase());
	$(this).val($(this).val().replace(/[\{\}\[\]\/?.;:|\)*~`!^\-_<>@\$%&\\\=\(\'\"]|[가-힣]|[ㄱ-ㅎ]|[ㅏ-ㅣ]|[0-9]/gi,""));
});
//logout 
$("#logout").click(function() { //로그아웃 클릭
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href='<s:url action="logout" namespace="/login"/>';
		}
});
$(function() {
	var msg = $("#errmsg").val();
	if(msg != null && msg != ""){
		alert(msg);
	}
});

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addworkerjQuery.js?version=20170626"></script>
</html>