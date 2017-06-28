package kr.co.ican.util;

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
}
