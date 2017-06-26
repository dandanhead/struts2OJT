package kr.co.ican.util;

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
}
