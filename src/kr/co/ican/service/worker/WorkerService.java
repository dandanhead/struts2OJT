package kr.co.ican.service.worker;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

//Worker 관련 services
public interface WorkerService {
	
	//DB
	public List<MemberVO> getWorkerList(MemberVO mvo) throws SQLException;
	public int getWorkerCount() throws SQLException;
	public boolean chkDuplPhone(MemberVO mvo) throws SQLException;
	public boolean chkDuplEmail(MemberVO mvo) throws SQLException;
	public boolean chkDuplScnum(MemberVO mvo) throws SQLException;
	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) throws SQLException;
	
	//validation
	public boolean scnumValidation(String chksnumF, String chksnumE);// 주민번호 유효성 체크
	public boolean onlyInputNumber(String num);// 숫자만 입력 체크
	public boolean arrayNullCheck(String[] str);//배열값 널체크
	public boolean careerSdateCompareEdate(String sdate[], String edate[]) throws ParseException;// 경력 시작일이 종료일보다 먼저인지 체크 
	public boolean inputDateCompareCurrDate(String inputdate[]) throws ParseException;// 입력 날짜가 현재날짜보다 먼저인지 체크
	public boolean careerDuplCheckDate(String sdate[], String edate[]) throws ParseException;// 경력 중복 체크
	// make list Experience
	public List<ExperienceVO> makeListExp(String[] regi, String[] exit, String[] coname ,String[] auth , String[] roll , int count);
	public List<MemLicenseVO> makeListLicense(String[] lname, String[] acqdate, String[] organization, int count);
}
