package kr.co.ican.service.worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

//Worker 관련 services
public class WorkerServiceImpl implements WorkerService {

	@Override
	public List<MemberVO> getWorkerList( MemberVO mvo) throws SQLException {
	    Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		WorkerDAO wdao = new WorkerDAO();
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		conn = GetDBConn.getConnection();
		// Paging
		int pageNumber = mvo.getPageNumber(); //current Page Number
		int start = (pageNumber)*mvo.getRecordCountPerPage() + 1; // List Strat Number
		int end = (pageNumber + 1)*mvo.getRecordCountPerPage(); //List End Number
		mvo.setStart(start);
		mvo.setEnd(end);
		mlist = wdao.getWorkerList(conn, psmt, rs, mvo);
		
		GetDBConn.close(conn, psmt, rs);
		
		return mlist;
	}

	@Override
	public int getWorkerCount() throws SQLException {// Total counting Member
	    Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		conn = GetDBConn.getConnection();
		
		int totalcount = 0;
		WorkerDAO wdao = new WorkerDAO();
		
		totalcount = wdao.getWorkerCount(conn, psmt, rs); 
		
		GetDBConn.close(conn, psmt, rs);
		
		return totalcount;
		
	}

	@Override
	public boolean chkDuplPhone(MemberVO mvo) throws SQLException {// Check Duplicate Phone Number
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		
		flag = wdao.chkDuplPhone(mvo, conn, psmt, rs);
		
		GetDBConn.close(conn, psmt, rs);
		
		return flag;
	}

	@Override
	public boolean chkDuplEmail(MemberVO mvo) throws SQLException { // Check Duplicate E-Mail Address
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		flag = wdao.chkDuplEmail(mvo, conn, psmt, rs);
		GetDBConn.close(conn, psmt, rs);
		return flag;
	}

	@Override
	public boolean chkDuplScnum(MemberVO mvo) throws SQLException {// Check Duplicate Social Number
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		flag = wdao.chkDuplScnum(mvo, conn, psmt, rs);
		GetDBConn.close(conn, psmt, rs);
		return flag;
	}

	@Override
	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) throws SQLException{ // add Worker
		//Insert Check Flag
		boolean basicInfoFlag = false;
		boolean licenseFlag = false;
		boolean basicExpFlag = false;
		boolean careerFlag = false;
		//Create
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		conn = GetDBConn.getConnection();
		conn.setAutoCommit(false);
		
		WorkerDAO wdao = new WorkerDAO();
		//1. 기본정보 and 기본 경력 추가
		basicInfoFlag = wdao.addWorkerInfo(mvo, conn, psmt, rs); // 기본 정보
		basicExpFlag = wdao.basicWorkerExp(mvo, conn, psmt, rs); // 기본 경력
		//2. 자격증
		if(liclist != null && liclist.size() > 0){
			
			for (int idx = 0; idx < liclist.size(); idx++) {
				
				licenseFlag = wdao.addWorkerLicense(liclist.get(idx), conn, psmt, rs);
				if(!licenseFlag){
					break;
				}
			}
		}else{
			licenseFlag = true;
		}
		//3. 경력
		if (elist != null && elist.size() > 0) {
			
			for (int idx = 0; idx < elist.size(); idx++) {
				
				careerFlag = wdao.addWorkerExp(elist.get(idx), conn, psmt, rs);
				if(!careerFlag){
					break;
				}
			}
		}else{
			careerFlag =true;
		}
		
		//4. insert check
		if(!basicInfoFlag || !licenseFlag || !basicExpFlag || !careerFlag){
			conn.rollback(); //roll back
			GetDBConn.close(conn, psmt, rs);
			return false;
		}else{
			conn.commit(); // commit
			GetDBConn.close(conn, psmt, rs);
			return true;
		}
		
	}
	//for validation
	@Override
	public boolean scnumValidation(String chksnumF, String chksnumE) {

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

	@Override
	public boolean onlyInputNumber(String num) {

		for (int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);

			if (ch < '0' || ch > '9') {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean arrayNullCheck(String[] str) {
		for (int idx = 0; idx < str.length; idx++) {
			if(("").equals(str[idx])){
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean careerSdateCompareEdate(String[] sdate, String[] edate) throws ParseException {

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

	@Override
	public boolean inputDateCompareCurrDate(String[] inputdate) throws ParseException {
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

	@Override
	public boolean careerDuplCheckDate(String[] sdate, String[] edate) throws ParseException {
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

	@Override
	public List<ExperienceVO> makeListExp(String[] regi, String[] exit, String[] coname, String[] auth, String[] roll,
			int count) {
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

	@Override
	public List<MemLicenseVO> makeListLicense(String[] lname, String[] acqdate, String[] organization, int count) {
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
