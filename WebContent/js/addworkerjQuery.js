var chkTa = false; //타업체선택 유무
var liccnt = 0; //자격증 row count
var cnt = 0; //경력 row 카운트
// logout 
$("#logout").click(function() { //로그아웃 클릭
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href="logout.do";
		}
});
// 타업체 인력 input tag 추가 (회사명 입력)
$("#conamelabel").hide();
$("input[name='outsideperson']").hide();

$("select[name='im_dname']").change(function () {
	if("타업체인력" == $(this).val()){
		$("#conamelabel").show();
		$("input[name='outsideperson']").show();
		chkTa = true;
	}else{
		$("#conamelabel").hide();
		$("input[name='outsideperson']").hide();
		chkTa = false;
	}
});
// 자격증 추가
$("#addLicense").click(function () {
	
	var appendhtml = '<tr>';
	    appendhtml +=    '<td><input class="form-control" type="text" style="width: 100%;" name="iml_lname"></td>';
	    appendhtml +=    '<td><input class="form-control" type="text" style="width: 100%;" name="iml_acudate" readonly="readonly"></td>';
	    appendhtml +=    '<td><input class="form-control" type="text" style="width: 100%;" name="iml_organization"></td>';
	    appendhtml +=    '<td><a class="btn btn-default">cancle</a></td>';
	    appendhtml +='</tr>';
	    
	    liccnt++;
	$("#ltb").append(appendhtml);
	
	//datepicker 설정
    $( "input[name='iml_acudate']" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
});

// 경력 추가 
$("#addCareers").click(function(){ //경력 row 추가 버튼
    
	// html row 추가
    var html = '<tr class="trappend">';
        html +=    '<td><input class="form-control" type="text" style="width: 100%;" name="ime_regi_date" readonly="readonly"></td>';
    	html +=	   '<td><input class="form-control abc" type="text" style="width: 100%;" name="ime_exit_date" readonly="readonly"></td>';
    	html +=    '<td><input class="form-control abc" type="text" style="width: 100%;" name="ime_coname"></td>';
    	html +=    '<td>';
    	html +=   	   '<select class="form-control" style="width: 80%;" name="ime_auth">';
    	html +=   	 	  '<option selected="selected" value="0">Developer</option>';
    	html +=    		  '<option value="1">Manager</option>';
    	html +=   	   '</select>  ';
    	html +=    '</td>';
    	html +=    '<td><input class="form-control" type="text" style="width: 100%;" name="ime_roll"></td>';
    	html +=    '<td><a class="btn btn-default">cancle</a></td>';
    	html +='</tr>';
	cnt++; //row count
    $("#ctb").append(html); 
    
	//datepicker 설정
    $( "input[name='ime_regi_date']" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
    $( "input[name='ime_exit_date']" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
    
});

// 경력 삭제
$("#ctb").on("click", ".btn-default", function() { //경력 row 삭제 버튼
	cnt--;
	$(this).parent().parent().remove();
});
// 자격증 삭제
$("#ltb").on("click", ".btn-default", function() { //자격증 row 삭제 버튼
	liccnt--;
	$(this).parent().parent().remove();
});
// 사원 추가
$("#addbtn").click(function() {
	
	var chknum = 0;	// 체크박스 체크 갯수
	var isS = false; // 중복체크(주민등록번호, 이메일, 핸드폰번호 ajax 를 이용한 중복체크)
	var arrSdate = []; //경력 시작일 배열담기
	var arrEdate = []; //경력 종료일 배열담기
	var arrConame = []; //회사명 배열담기
	var arrRoll = []; //역할 배열 담기
	
	var arrLicname = []; //자격증 이름 배열담기
	var arrAcudate = []; //자격증 취득일 배열담기
	var arrOrg = []; // 자격증 발급처 배열담기
	
 	//항목 null check
	var chkpw = $.trim($("#chkpw").val());
	var chkcpw = $.trim($("#confirmpw").val());
	var chkname = $.trim($("#chkname").val());
	var snumF = $.trim($("#snumF").val());
	var snumE = $.trim($("#snumE").val());
	var sphone = $.trim($("#sphone").val());
	var mphone = $.trim($("#mphone").val());
	var ephone = $.trim($("#ephone").val());
	var chkemail = $.trim($("#chkemail").val()); // 메일
	var chkconame = $.trim($("#coname").val()); // 타업체 회사명
	var tempaddress =  $.trim($("#tempaddress").val()); // 도로 or 지번 주소
	var detailaddress = $.trim($("#detailaddress").val()); // 상세 주소
	var postcode = $.trim($("#postcode").val()); // 우편번호
	var chkskill = $.trim($("#skillinput").val());
	var snum = snumF + "-" + snumE; // 주민등록번호
	var phonenum = sphone + "-" + mphone + "-" + ephone; // 폰번
	
	//hidden 태그에 값 넣기
	$("#chkphone").val(phonenum);
	$("#chkscnum").val(snum);

	// 경력중복 체크(입력 받은 값 배열에 넣기)
	$("input[name='ime_regi_date']").each(function() {
		arrSdate.push($(this).val());
	});
	$("input[name='ime_exit_date']").each(function() {
		arrEdate.push($(this).val());
	});
	$("input[name='ime_coname']").each(function() {
		arrConame.push($(this).val());
	});
	$("input[name='ime_roll']").each(function () {
		arrRoll.push($(this).val());
	});
	// 자격증 취득일 배열에 넣기
	$("input[name='iml_lname']").each(function () {
		arrLicname.push($(this).val());
	});
	$("input[name='iml_acudate']").each(function () {
		arrAcudate.push($(this).val());
	});
	$("input[name='iml_organization']").each(function () {
		arrOrg.push($(this).val());
	});
	// 이메일, 전화번호, 주민등록 번호 중복 체크
	$.ajax({
			type : 'GET',
			url : 'checkDuplicate.do',
			async: false,
			data : { "ajaxeamil" : chkemail,
					 "ajaxphone" : phonenum,
					 "ajaxscnum" : snum },
			
			success : function(data) {
				if(!data){
					isS = true;					
				}
			}		
	});
	
	jQuery.ajaxSettings.traditional = true; // 깨짐 방지
	// 경력 중복 체크
	var expErrChk = 0;
	for (var idx = 0; idx < cnt; idx++) {
		
		for (var iddx = 0; iddx < cnt; iddx++) {
			
			if(idx != iddx){
				
				if(arrSdate[idx] <= arrSdate[iddx] && arrSdate[iddx] <= arrEdate[idx]){
					expErrChk++;
				}
				if(arrSdate[idx] <= arrEdate[iddx] && arrEdate[iddx] <= arrEdate[idx]){
					expErrChk++;
				}
			}
		}
	}
	//경력 빈칸 체크 , 
	var expEmptyChk = 0;
	//시작날짜가 종료날짜 보다 이후일 때 
	var errTerm = 0;
	//현재 날짜 이후날짜를 입력 했을 때 
	var currDate = 0;
	//현재날짜 구하기
    var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    var chan_val = year + '-' + mon + '-' + day;
    
	for (var i = 0;  i  < cnt; i++) {
		// 빈칸 일때
		if(arrSdate[i] == "" || arrEdate[i] == "" || arrConame[i] == "" || arrRoll[i] == ""){
			expEmptyChk++;
		}
		// 시작 날짜가 종료 날짜보다 이후 일때
		if(arrEdate[i] <= arrSdate[i]){
			errTerm++;
		}
		// 시작 또는 종료 날짜가 현재 이후일때
		if(arrEdate[i] >= chan_val || arrSdate[i] >= chan_val){
			currDate++;
		}
	}
	//자격증 취득일이 현재 날짜 이후인지 체크
	var licdatecurr = 0;
	var licempty = 0;
	for (var j = 0; j < liccnt; j++) {
		if(arrAcudate[j] >= chan_val){
			licdatecurr++;
		}
		if(arrLicname[j] == "" || arrAcudate[j] == "" || arrOrg[j] == ""){
			licempty++;
		}
	}
	//validation
	var languageCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z]/; // 문자열 체크
	// 주민등록 번호 유효성 체크
	function isValidDate(snumF, snumE) {
        try
        {
            var tempnum = Number(snumE.substring(0, 1)); //성별, 19xx, 20xx 년생 확인  1,2,3,4...
            
        	if(tempnum > 2){
        		snumF = "20"+snumF;
        	}else{
        		snumF = "19"+snumF;
        	}
        	
            var year = Number(snumF.substring(0, 4)); // 1985
            var month = Number(snumF.substring(4, 6)); // 05	
            var day = Number(snumF.substring(6, 8)); // 29
 
            var dd = day / 0;
            // 월 체크
            if( month<1 || month>12 ) {
                return false;
            }
             
            var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            var maxDay = maxDaysInMonth[month-1];
             
            // 윤년 체크
            if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
                maxDay = 29;
            }
            // 일 체크 
            if( day<=0 || day>maxDay ) {
                return false;
            }
            // 뒷자리 체크(주민번호 유효성)
            var sumNum = snumF + snumE; // ex)198505291839190
            sumNum = sumNum.substring(2, 15); 
            var maxlength = sumNum.length;
            var lastnum = sumNum.charAt(maxlength -1);
            var result = 0;
            for(var i = 0; i < maxlength - 1; i++){
            	
            	if(i > 7){
            		result += (i+2-8) * sumNum.charAt(i);
            	}else{
            		result += (i+2) * sumNum.charAt(i);
            	}
            }
            
            result = 11-(result % 11);
            
            if(lastnum == result){
            	return true;
            }else{
            	return false;
            }
 
        } catch (err) {
            return false;
        }                       
    }
	
	//validation
	if( chkpw == null || chkpw == ""){
		alert("PassWord를 입력해 주세요");
	}else if(chkpw != chkcpw){
		alert("패스워드가 일치하지 않습니다.");
	}else if(chkpw.length < 6 || chkpw.length > 16){
		alert("패스워드는 6자리 이상 15자리 이하로 입력해 주세요");
	}else if( chkname == null || chkname == ""){
		alert("이름을 입력해 주세요.");
	}else if(languageCheck.test(snum)){
		alert("주민등록번호에는 문자열이 포함 될 수 없습니다.");
	}else if(snum == null || snum == ""){
		alert("주민등록 번호는 공백일 수 없습니다.");
	}else if(languageCheck.test(phonenum)){
		alert("핸드폰 번호는 숫자만 입력이 가능합니다.");
	}else if(snum.length != 14){
		alert("주민등록번호 13자리를 올바르게 입력해 주세요.");
	}else if(!isValidDate(snumF, snumE)){
		alert("유효하지 않은 주민등록 번호 입니다.");
	}else if(phonenum.length < 12 || phonenum.length > 13){
		alert("핸드폰 번호를 정확하게 입력해 주세요.");
	}else if(isS){
		alert("이미 등록된 이메일, 주민등록번호, 핸드폰번호 입니다.");
	}else if(chkemail == null || chkemail == ""){
		alert("E-Mail 을 올바르게 입력 해 주세요.");
	}else if(chkTa == true && chkconame == ""){
		alert("타업체인력인 경우 반드시 회사명을 입력해 주세요.");
	}else if(postcode == null || postcode == ""){
		alert("우편번호를 정확히 입력 해 주세요.");
	}else if(tempaddress == "" || tempaddress == null){
		alert("주소를 정확히 입력해 주세요. 우편 번호 찾기를 클릭하여 도로 또는 지번 주소를 입력해 주세요.");
	}else if(detailaddress == "" || detailaddress == null){
		alert("주소를 정확히 입력해 주세요. 주소는 공백일 수 없습니다.");
	}else if(chkskill == "" || chkskill == null){
		alert("사용가능한 언어와 환경을 하나 이상 적어주세요. ");
	}else if(cnt > 0 || liccnt > 0){
		if(expEmptyChk > 0){
			alert("경력란에 입력하지 않은 값이 있습니다. 정확하게 입력해 주세요");
		}else if(currDate > 0){
			alert("경력시작 또는 종료일은 현재 날짜 보다 이전 날짜 이어야 합니다.");
		}else if(errTerm > 0){
			alert("경력 시작일은 종료일 보다 늦을 수 없습니다.");
		}else if(expErrChk > 0){
			alert("중복된 경력 기간이 존재합니다. 정확하게 입력 해 주세요.");
		}else if(licdatecurr > 0){
			alert("자격증 취득날짜가 현재 날짜보다 이후입니다. 정확히 입력해 주세요.");
		}else 	if(licempty > 0){
			alert("자격증란에 입력하지 않은 값이 있습니다. 정확하게 입력해 주세요.");
		}else{
			if(confirm("사원을 추가 하시겠습니까?")){
				
				$("#frm").attr({"target" : "_self" , "action" : "adWorkerAfter"}).submit();
			}
		}
	}else if(cnt == 0 ){
		if(confirm("경력사항을 입력하지 않았습니다. 계속 진행하시겠습니까? ")){
			$("#frm").attr({"target" : "_self" , "action" : "adWorkerAfter"}).submit();
		}
	}else{
		alert("Page Err, 관리자에게 문의하세요.");
	}
});