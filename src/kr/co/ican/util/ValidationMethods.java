package kr.co.ican.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationMethods {

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
	public boolean isMailFormCheck(String chkemail){
		chkemail = chkemail.trim().replaceAll(" ", "");
		if(chkemail.indexOf('@') == -1){
			return false; //false == 형식 아니다
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
	public boolean dateCompartFromTo(String[] sdate, String[] edate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;
		
		try {
			// 시작일 종료일 날짜 비교
			for (int idx = 0; idx < sdate.length; idx++) {

				startdate = dateFormat.parse(sdate[idx]);
				enddate = dateFormat.parse(edate[idx]);
				
				if (startdate.compareTo(enddate) >= 0) {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	//compare from date to current date
	public boolean dateCompareCurr(String[] inputdate){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date comparedate = null; // 입력받은 날짜
		
		try {
			Date curdate = dateFormat.parse(dateFormat.format(new Date())); // 현재날짜
			
			for (int idx = 0; idx < inputdate.length; idx++) {
				
				comparedate = dateFormat.parse(inputdate[idx]);
				
				if(comparedate.compareTo(curdate) >= 0){
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	// duplicate check Dates
	public boolean duplChkDate(String[] sdate, String[] edate)  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;
		Date startnext = null;
		Date endnext = null;
		try {
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
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean stringLengthCheck(String chkstr){
		chkstr = chkstr.trim().replaceAll(" ", "");
		if(chkstr.length() < 6 || chkstr.length() > 15){
			return false;
		}else{
			return true;
		}
	}
	
	//for validation
	public boolean scnumValidation(String chksnumF, String chksnumE) {

		if (chksnumF.length() != 6 && chksnumE.length() != 7) {
			return false;
		}

		String gender = chksnumE.substring(0, 1);
		int gendernum = Integer.parseInt(gender);

		if (gendernum > 2) {
			chksnumF = "20" + chksnumF;
		} else {
			chksnumF = "19" + chksnumF;
		}

		int year = Integer.parseInt(chksnumF.substring(0, 4)); // 19xx
		int month = Integer.parseInt(chksnumF.substring(4, 6)); // 09
		int day = Integer.parseInt(chksnumF.substring(6, 8)); // 24

		// 월 체크
		if (month < 1 || month > 12) {
			return false;
		}

		int maxDaysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int maxDay = maxDaysInMonth[month - 1];

		// 윤년 체크
		if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
			maxDay = 29;
		}

		// 일 체크
		if (day <= 0 || day > maxDay) {
			return false;
		}

		// 주민번호 뒷자리 유효성 체크
		String fullscnum = chksnumF + chksnumE; // 198509101239901
		fullscnum = fullscnum.substring(2, 15); // 8809101928191
		int maxlength = fullscnum.length();

		int lastnum = Integer.parseInt(String.valueOf(fullscnum.charAt(maxlength - 1)));
		int result = 0;

		for (int idx = 0; idx < maxlength - 1; idx++) {
			if (idx > 7) {
				result += (idx + 2 - 8) * Integer.parseInt(String.valueOf(fullscnum.charAt(idx)));
			} else {
				result += (idx + 2) * Integer.parseInt(String.valueOf(fullscnum.charAt(idx)));
			}
		}

		result = 11 - (result % 11);

		if (lastnum == result) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean phoneLengthThree(String chkstr){
		chkstr = chkstr.trim().replaceAll(" ", "");
		if(chkstr.length() != 3){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean phoneLengthThreeOrFour(String chkstr){
		chkstr = chkstr.trim().replaceAll(" ", "");
		if(chkstr.length() > 4 || chkstr.length() < 3){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean equalStringCheck(String chkstr, String comparestr){
		chkstr = chkstr.trim().replaceAll(" ", "");
		comparestr = comparestr.trim().replaceAll(" ", "");
		if(chkstr.equals(comparestr)){
			return true;
		}else{
			return false;
		}
		
	}
}
