package kr.co.ican.util;

import java.util.Calendar;

import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.vo.MemberVO;

// method Utility
public class Helps {

	public static final String NS = "/WEB-INF/views/";
	
	
	// ican_member table 의 직급은 int 값이므로 대응하는 직급으로 변환
	public String selectAuthority(int num){
		String result = "";
		if(num == 0){
			result = "Developer";
		}else{
			result = "Manager";
		}
		
		return result;
	}
	
	// Date Format yyyyMMdd
	public String yymmdd(String ymd){
		
		String result = "";
		
		if("".equals(ymd) || ymd == null){
			return result;
		}else{
			result = ymd.substring(0, 10);
			return result;
		}
	}
	
	//paging
	public MemberVO setWorkerListPaging(MemberVO mvo){
		
		int sn=mvo.getPageNumber();
		int start=(sn)*mvo.getRecordCountPerPage() + 1; // rownum 의 시작
		int end=(sn+1)*mvo.getRecordCountPerPage(); // rownum의 끝
		int recordCountperPage = mvo.getRecordCountPerPage();
		
		mvo.setStart(start); // rownum 의 시작
		mvo.setEnd(end); // rownum의 끝
		mvo.setRecordCountPerPage(recordCountperPage);
		
		return mvo;
	}
	
	public ProjectVO setProjectListPaging(ProjectVO pvo){
		
		int sn=pvo.getPageNumber();
		int start=(sn)*pvo.getRecordCountPerPage() + 1; // rownum 의 시작
		int end=(sn+1)*pvo.getRecordCountPerPage(); // rownum의 끝
		int recordCountperPage = pvo.getRecordCountPerPage();
		
		pvo.setStart(start);
		pvo.setEnd(end);
		pvo.setRecordCountPerPage(recordCountperPage);
		
		return pvo;
		
	}
	
	//chang Form Career
	public String changFormCareer(String expy, String expm){
		
		String experience = "";
		
		if("0".equals(expy) && "0".equals(expm)){
			experience = "-";
		}else if ("0".equals(expy) && !"0".equals(expm)) {
			experience = expm + "개월";
		}else if(!"0".equals(expy) && "0".equals(expm)){
			experience = expy + "년";
		}else{
			experience = expy + "년 " + expm + "개월"; 
		}
		
		
		return experience;
	}
	
	
	
	//5. 생년월일 , 나이 , 성별  계산
	// params = 주민번호
	// return  = age(int), gender(String)
	
	public int getWorkerAge(String im_scnum){
		
		int age = 0;
		
		String stnum = im_scnum.substring(0, 6); // 850910
		String agenum = stnum.substring(0, 2); // 85
		String ednum = im_scnum.substring(7, 14); // 1698701
		String gennum = ednum.substring(0, 1); // // 1
		
		int gendernum = Integer.parseInt(gennum);
		if(gendernum < 3){
			agenum = "19" + agenum;
		}else{
			agenum = "20" + agenum;  
		}
		
		int yage = Integer.parseInt(agenum);
		int yy = Calendar.getInstance().get(Calendar.YEAR); //현재 년도
		
		age = yy - yage; // return 
		
		return age;
	}
	
	public String getWorkerGender(String im_scnum){
		
		String resultstr = "";
		
		String ednum = im_scnum.substring(7, 14); // 1698
		String gennum = ednum.substring(0, 1); // // 1
		
		int gendernum = Integer.parseInt(gennum);
		//성별
		if((gendernum % 2) == 0){
			resultstr = "F";
		}else{
			resultstr = "F";
		}
		
		return resultstr;
	}
	
	
}
