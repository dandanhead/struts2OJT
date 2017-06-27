package kr.co.ican.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validations {

	//null check
	public boolean isblank(String chkstr) {

		String checkstr = "";
		
		checkstr = chkstr.trim().replaceAll(" ", "");
		
		if (checkstr == null || ("").equals(checkstr)) {

			return false;
			
		} else {
			
			return true;
		}
	}
	// input number check
	public boolean onlyInputNumber(String num) {

		for (int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);

			if (ch < '0' || ch > '9') {
				return false;
			}
		}
		return true;
	}
	
	// number blank check
	public boolean isScnumblank(int chknum) {

		if (chknum == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	// eamil form check
	public boolean isMailFormCheck(String chkeamil){
		
		String checkemail = "";
		checkemail = checkemail.trim().replaceAll(" ", "");
		
		if(checkemail.indexOf("@") == -1){
			return false;
		}else{
			return true;
		}
	}
	// array null check
	public boolean arrayNullCheck(String[] str) {
		for (int idx = 0; idx < str.length; idx++) {
			if(("").equals(str[idx])){
				return false;
			}
		}
		
		return true;
	}
	
	// compare from date to date
	public boolean dateCompartFromTo(String[] sdate, String[] edate) throws ParseException {

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
	
	//compare from date to current date
	public boolean dateCompareCurr(String[] inputdate) throws ParseException {
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
	
	// duplicate check Dates
	public boolean duplChkDate(String[] sdate, String[] edate) throws ParseException {
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
}
