package kr.co.ican.action.worker;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.service.worker.WorkerService;
import kr.co.ican.service.worker.WorkerServiceImpl;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

public class AddWorkerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private MemberVO mvo;
	
	private String confirmPw; // pw dupl check
	
	private String snumF; // 주민번호 앞자리
	private String snumE; // 주민번호 뒷자리
	
	private String sphone; //전화번호 앞자리
	private String mphone; // 중간자리
	private String ephone; // 끝자리
	
	//license
	private int chkLicense; // license count
	private String[] iml_lname;
	private String[] iml_acqdate;
	private String[] iml_organization;
	
 	//타업체
	private String outsideperson;
	private int chkTa;
	//career
	private int chkCareer; //career count
	private String[] ime_regi_date;
	private String[] ime_exit_date;
	private String[] ime_coname;
	private String[] ime_auth;
	private String[] ime_roll;
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	public String getSnumF() {
		return snumF;
	}

	public void setSnumF(String snumF) {
		this.snumF = snumF;
	}

	public String getSnumE() {
		return snumE;
	}

	public void setSnumE(String snumE) {
		this.snumE = snumE;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getEphone() {
		return ephone;
	}

	public void setEphone(String ephone) {
		this.ephone = ephone;
	}

	public String getOutsideperson() {
		return outsideperson;
	}

	public void setOutsideperson(String outsideperson) {
		this.outsideperson = outsideperson;
	}
	
	public String[] getIml_lname() {
		return iml_lname;
	}

	public void setIml_lname(String[] iml_lname) {
		this.iml_lname = iml_lname;
	}

	public String[] getIml_acqdate() {
		return iml_acqdate;
	}

	public void setIml_acqdate(String[] iml_acqdate) {
		this.iml_acqdate = iml_acqdate;
	}

	public String[] getIml_organization() {
		return iml_organization;
	}

	public void setIml_organization(String[] iml_organization) {
		this.iml_organization = iml_organization;
	}

	public String[] getIme_regi_date() {
		return ime_regi_date;
	}

	public void setIme_regi_date(String[] ime_regi_date) {
		this.ime_regi_date = ime_regi_date;
	}

	public String[] getIme_exit_date() {
		return ime_exit_date;
	}

	public void setIme_exit_date(String[] ime_exit_date) {
		this.ime_exit_date = ime_exit_date;
	}

	public String[] getIme_coname() {
		return ime_coname;
	}

	public void setIme_coname(String[] ime_coname) {
		this.ime_coname = ime_coname;
	}

	public String[] getIme_auth() {
		return ime_auth;
	}

	public void setIme_auth(String[] ime_auth) {
		this.ime_auth = ime_auth;
	}

	public String[] getIme_roll() {
		return ime_roll;
	}

	public void setIme_roll(String[] ime_roll) {
		this.ime_roll = ime_roll;
	}
	
	public int getChkTa() {
		return chkTa;
	}

	public void setChkTa(int chkTa) {
		this.chkTa = chkTa;
	}
	
	public int getChkLicense() {
		return chkLicense;
	}

	public void setChkLicense(int chkLicense) {
		this.chkLicense = chkLicense;
	}

	public int getChkCareer() {
		return chkCareer;
	}

	public void setChkCareer(int chkCareer) {
		this.chkCareer = chkCareer;
	}
	
	public String validation() throws SQLException, ParseException{
		
		boolean chkInsert = false;
		//service 
		WorkerService wservice = new WorkerServiceImpl();
		// trim and delete space
		String chkpw = mvo.getIm_pw().trim().replace(" ", "");
		String chkpwconfirm = confirmPw.trim().replace(" ", "");
		String chkname = mvo.getIm_name().trim().replace(" ", "");
		String chksnumF = snumF.trim().replace(" ", "");
		String chksnumE = snumE.trim().replace(" ", "");
		String chksphone = sphone.trim().replace(" ", "");
		String chkmphone = mphone.trim().replace(" ", "");
		String chkephone = ephone.trim().replace(" ", "");
		String chkemail = mvo.getIm_email().trim().replace(" ", "");
		String chkpostcode = mvo.getIm_postcode().trim().replace(" ", "");
		String chkaddress = mvo.getIm_address().trim();
		String chkdetailaddr = mvo.getIm_detailaddr().trim();
		String chkskill = mvo.getIm_skill().trim().replace(" ", "");
		String outsidecompany = outsideperson.trim();
		
		//메일, 핸드폰 번호, 주민번호  vo setting
		mvo.setIm_email(chkemail);
		mvo.setIm_phone(chksphone+"-"+chkmphone+"-"+chkephone);
		mvo.setIm_scnum(chksnumF+"-"+chksnumE);
		
		//중복 체크 이메일. 폰번호, 주민번호 
		boolean emailflag = wservice.chkDuplEmail(mvo);
		boolean phonelflag = wservice.chkDuplPhone(mvo);
		boolean scnumflag = wservice.chkDuplScnum(mvo);
		
		//service 에 보낼 자격증, 경력사항 LIST init
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		
		//validation
		if(chkpw == null || ("").equals(chkpw) || chkpwconfirm == null || ("").equals(chkpwconfirm)){// pw 공백 체크
			addFieldError("chkpw", "패스워드를 입력해 주세요.");
			return "fail";
		}else if(!chkpw.equals(chkpwconfirm)){// pw 일치
			addFieldError("chkpwconfirm", "패스워드가 일치 하지 않습니다.");
			return "fail";
		}else if(chkpw.length() < 6 || chkpw.length() > 15){// pw 길이
			addFieldError("chkpw", "패스워드는 6자리 이상 15자리 이하로 입력해 주세요.");
			return "fail";
		}else if(chkname == null || ("").equals(chkname)){// 이름 공백
			addFieldError("chkname", "이름을 입력해 주세요.");
			return "fail";
		}else if(chksnumF == null || ("").equals(chksnumF) || chksnumE == null || ("").equals(chksnumE)){
			addFieldError("chkscnum", "주민등록번호를 입력 해 주세요.");
			return "fail";
		}else if(!onlyInputNumber(chksnumF) || !onlyInputNumber(chksnumE)){
			addFieldError("chkscnum", "주민등록번호는 숫자만 입력 가능합니다.");
			return "fail";
		}else if(!scnumValidation(chksnumF, chksnumE)){
			addFieldError("chkscnum", "유효한 주민등록번호가 아닙니다.");
			return "fail";
		}else if(scnumflag){
			addFieldError("chkscnum", "이미 등록된 주민등록번호 입니다.");
			return "fail";
		}else if(!onlyInputNumber(chksphone) || !onlyInputNumber(chkmphone) || !onlyInputNumber(chkephone)){
			addFieldError("chkphone", "전화번호는 숫자만 입력가능합니다.");
			return "fail";
		}else if(chksphone.length() != 3 || chkmphone.length() > 4 || chkmphone.length() < 3 || chkephone.length() != 4){
			addFieldError("chkphone", "전화번호를 정확히 입력해 주세요.");
			return "fail";
		}else if(phonelflag){
			addFieldError("chkphone", "이미 등록된 핸드폰번호 입니다.");
			return "fail";
		}else if(chkemail == null || ("").equals(chkemail)){
			addFieldError("chkemail", "이메일 주소를 입력해 주세요.");
			return "fail";
		}else if(chkemail.indexOf("@") == -1){
			addFieldError("chkemail", "이메일 주소 형식이 아닙니다.");
			return "fail";
		}else if(emailflag){
			addFieldError("chkemail", "이미 등록된 이메일 입니다.");
			return "fail";
		}else if(chkTa == 1 && ("").equals(outsidecompany)){
			addFieldError("chkTaCoName", "타업체 인력인 경우 회사명을 반드시 입력해야 합니다.");
			return "fail";
		}else if(chkpostcode == null || ("").equals(chkpostcode)){
			addFieldError("chkaddress", "우편번호를 입력해 주세요.");
			return "fail";
		}else if(chkaddress == null || ("").equals(chkaddress)){
			addFieldError("chkaddress", "주소를 입력해 주세요.");
			return "fail";
		}else if(chkdetailaddr == null || ("").equals(chkdetailaddr)){
			addFieldError("chkaddress", "상세주소를 입력해 주세요.");
			return "fail";
		}else if(chkskill == null || ("").equals(chkskill)){
			addFieldError("chkskill", "사용스킬을 입력해 주세요.");
			return "fail";
		}else if(chkLicense > 0 || chkCareer > 0){
			
			if(chkLicense == 0 && chkCareer > 0){ // 자격증 x 경력 o
				
				if(!arrayNullCheck(ime_regi_date) || !arrayNullCheck(ime_exit_date) || !arrayNullCheck(ime_auth) || !arrayNullCheck(ime_roll)){
					addFieldError("chkcareer", "경력사항에 입력하지 않은 값이 있습니다.");
					return "fail";
				}
				if(!careerSdateCompareEdate(ime_regi_date, ime_exit_date)){
					addFieldError("chkcareer", "경력 종료일이 시작일 보다 먼저일 수 없습니다.");
					return "fail";
				}
				if(!inputDateCompareCurrDate(ime_regi_date) || !inputDateCompareCurrDate(ime_exit_date)){
					addFieldError("chkcareer", "경력 시작일 또는 종료일은 현재날짜보다 같거나 이후 일 수 없습니다.");
					return "fail";
				}
				if(!careerDuplCheckDate(ime_regi_date, ime_exit_date)){
					addFieldError("chkcareer", "중복된 경력기간이 존재합니다.");
					return "fail";
				}
				
				elist = makeListExp(ime_regi_date, ime_exit_date, ime_coname, ime_auth , ime_roll, chkCareer);
				chkInsert = wservice.addWorker(mvo, elist, liclist);
				if(chkInsert){
					return "success";
				}else{
					return "dberr";
				}
				
				
			}else if(chkCareer == 0 && chkLicense > 0){
				//자격증 o 경력 x
				if(!arrayNullCheck(iml_lname) || !arrayNullCheck(iml_acqdate) || !arrayNullCheck(iml_organization)){
					addFieldError("chklicense", "자격증 항목에 입력하지 않은 값이 있습니다.");
					return "fail";
				}
				if(!inputDateCompareCurrDate(iml_acqdate)){
					addFieldError("chklicense", "자격증 취득일은 현재 날짜 보다 이후 일 수 없습니다.");
					return "fail";
				}
				
				liclist = makeListLicense(iml_lname, iml_acqdate ,iml_organization , chkLicense);
				chkInsert = wservice.addWorker(mvo, elist, liclist);
				if(chkInsert){
					return "success";
				}else{
					return "dberr";
				}
				
			}else{
				//자격증 o 경력 o
				if(!arrayNullCheck(ime_regi_date) || !arrayNullCheck(ime_exit_date) || !arrayNullCheck(ime_auth) || !arrayNullCheck(ime_roll)){
					addFieldError("chkcareer", "경력사항에 입력하지 않은 값이 있습니다.");
					return "fail";
				}
				if(!careerSdateCompareEdate(ime_regi_date, ime_exit_date)){
					addFieldError("chkcareer", "경력 종료일이 시작일 보다 먼저일 수 없습니다.");
					return "fail";
				}
				if(!inputDateCompareCurrDate(ime_regi_date) || !inputDateCompareCurrDate(ime_exit_date)){
					addFieldError("chkcareer", "경력 시작일 또는 종료일은 현재날짜보다 같거나 이후 일 수 없습니다.");
					return "fail";
				}
				if(!careerDuplCheckDate(ime_regi_date, ime_exit_date)){
					addFieldError("chkcareer", "중복된 경력기간이 존재합니다.");
					return "fail";
				}
				if(!arrayNullCheck(iml_lname) || !arrayNullCheck(iml_acqdate) || !arrayNullCheck(iml_organization)){
					addFieldError("chklicense", "자격증 항목에 입력하지 않은 값이 있습니다.");
					return "fail";
				}
				if(!inputDateCompareCurrDate(iml_acqdate)){
					addFieldError("chklicense", "자격증 취득일은 현재 날짜 보다 이후 일 수 없습니다.");
					return "fail";
				}
				elist = makeListExp(ime_regi_date, ime_exit_date, ime_coname, ime_auth , ime_roll, chkCareer);
				liclist = makeListLicense(iml_lname, iml_acqdate ,iml_organization , chkLicense);
				chkInsert = wservice.addWorker(mvo, elist, liclist);
				if(chkInsert){
					return "success";
				}else{
					return "dberr";
				}
			}
			
		}else{ //자격증 x 경력 x
			chkInsert = wservice.addWorker(mvo, elist, liclist);
			if(chkInsert){
				return "success";
			}else{
				return "dberr";
			}
		}
		
	}
	// 주민번호 유효성 체크
	private boolean scnumValidation(String chksnumF, String chksnumE){ 
		
		if(chksnumF.length() != 6 && chksnumE.length() != 7){
			return false;
		}
		
		String gender = chksnumE.substring(0, 1);
		int gendernum = Integer.parseInt(gender);
		
		if(gendernum > 2){
			chksnumF = "20" + chksnumF;
		}else{
			chksnumF = "19" + chksnumF; 
		}
		
		int year = Integer.parseInt(chksnumF.substring(0, 4)); // 19xx
		int month = Integer.parseInt(chksnumF.substring(4, 6)); // 09
		int day = Integer.parseInt(chksnumF.substring(6, 8)); // 24
		
		// 월 체크
		if(month < 1 || month > 12){
			return false;
		}
		
		int maxDaysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int maxDay = maxDaysInMonth[month -1];
		
		//윤년 체크
		if(month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)){
			maxDay = 29;
		}
		
		//일 체크
		if(day <= 0 || day > maxDay){
			return false;
		}
		
		//주민번호 뒷자리 유효성 체크
		String fullscnum = chksnumF + chksnumE; // 198509101239901
		fullscnum = fullscnum.substring(2, 15); // 8809101928191
		int maxlength = fullscnum.length();
		
		int lastnum = Integer.parseInt(String.valueOf(fullscnum.charAt(maxlength -1))); 
		int result = 0;
		
		for (int idx = 0; idx < maxlength -1; idx++) {
			if(idx > 7){
				result += (idx+2-8) * Integer.parseInt(String.valueOf(fullscnum.charAt(idx)));
			}else{
				result += (idx + 2) * Integer.parseInt(String.valueOf(fullscnum.charAt(idx)));
			}
		}
		
		result = 11 - (result % 11);
		
		if(lastnum == result){
			return true;
		}else{
			return false;
		}
		
	}
	// 숫자만 입력 체크
	private boolean onlyInputNumber(String num){ 

		
		for (int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);

			if (ch < '0' || ch > '9') {
				return false;
			}
		}
		return true;
	}
	//배열값 널체크
	private boolean arrayNullCheck(String[] str){ 
		
		for (int idx = 0; idx < str.length; idx++) {
			if(("").equals(str[idx])){
				return false;
			}
		}
		
		return true;
	}
	// 경력 시작일이 종료일보다 먼저인지 체크
	private boolean careerSdateCompareEdate(String sdate[], String edate[]) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;

		// 시작일 종료일 날짜 비교
		for (int idx = 0; idx < sdate.length; idx++) {

			startdate = dateFormat.parse(sdate[idx]);
			enddate = dateFormat.parse(edate[idx]);
			
			if (startdate.compareTo(enddate) >= 0) {
				return false;
			}
		}

		return true;
	}
	// 입력 날짜가 현재날짜보다 먼저인지 체크
	private boolean inputDateCompareCurrDate(String inputdate[]) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date comparedate = null; // 입력받은 날짜
		Date curdate = dateFormat.parse(dateFormat.format(new Date())); // 현재날짜
		
		for (int idx = 0; idx < inputdate.length; idx++) {
			
			comparedate = dateFormat.parse(inputdate[idx]);
			
			if(comparedate.compareTo(curdate) >= 0){
				return false;
			}
		}
		
		return true;

	}
	// 경력 중복 체크
	private boolean careerDuplCheckDate(String sdate[], String edate[]) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;
		Date startnext = null;
		Date endnext = null;
		
		for (int idx = 0; idx < sdate.length; idx++) {
			for (int iddx = 0; iddx < edate.length; iddx++) {
				if(idx != iddx){
					startdate = dateFormat.parse(sdate[idx]);
					enddate = dateFormat.parse(edate[idx]);
					startnext = dateFormat.parse(sdate[iddx]);
					endnext = dateFormat.parse(edate[iddx]);
					
					if(startdate.compareTo(startnext) <= 0 && startnext.compareTo(enddate) <= 0){
						return false;
					}
					if(startdate.compareTo(endnext) <= 0 && endnext.compareTo(enddate) <= 0){
						return false;
					}
					
				}
			}
		}
		
		return true;
	}
	
	// make list Experience
	private List<ExperienceVO> makeListExp(String[] regi, String[] exit, String[] coname ,String[] auth , String[] roll , int count){
		
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		
		for (int idx = 0; idx < count; idx++) {
			ExperienceVO evo = new ExperienceVO();
			evo.setIme_regi_date(regi[idx]);
			evo.setIme_exit_date(exit[idx]);
			evo.setIme_coname(coname[idx]);
			evo.setIme_auth(Integer.parseInt(auth[idx]));
			evo.setIme_roll(roll[idx]);
			elist.add(evo);
		}
		
		return elist;
	}
	
	private List<MemLicenseVO> makeListLicense(String[] lname, String[] acqdate, String[] organization, int count){
		
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		
		for (int idx = 0; idx < count; idx++) {
			MemLicenseVO licvo = new MemLicenseVO();
			licvo.setIml_lname(lname[idx]);
			licvo.setIml_acqdate(acqdate[idx]);
			licvo.setIml_organization(organization[idx]);
			liclist.add(licvo);
		}
		
		return liclist;
	}
 	
}
