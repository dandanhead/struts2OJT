package kr.co.ican.worker.vo;

import java.util.Arrays;

public class AddWorkerVO {

	private MemberVO mvo; // 각종 입력 정보들 
	
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
	private int chkTa;
	private String outsideperson;
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
	public int getChkLicense() {
		return chkLicense;
	}
	public void setChkLicense(int chkLicense) {
		this.chkLicense = chkLicense;
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
	public int getChkTa() {
		return chkTa;
	}
	public void setChkTa(int chkTa) {
		this.chkTa = chkTa;
	}
	public String getOutsideperson() {
		return outsideperson;
	}
	public void setOutsideperson(String outsideperson) {
		this.outsideperson = outsideperson;
	}
	public int getChkCareer() {
		return chkCareer;
	}
	public void setChkCareer(int chkCareer) {
		this.chkCareer = chkCareer;
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
	
	@Override
	public String toString() {
		return "AddWorkerVO [mvo=" + mvo + ", confirmPw=" + confirmPw + ", snumF=" + snumF + ", snumE=" + snumE
				+ ", sphone=" + sphone + ", mphone=" + mphone + ", ephone=" + ephone + ", chkLicense=" + chkLicense
				+ ", iml_lname=" + Arrays.toString(iml_lname) + ", iml_acqdate=" + Arrays.toString(iml_acqdate)
				+ ", iml_organization=" + Arrays.toString(iml_organization) + ", chkTa=" + chkTa + ", outsideperson="
				+ outsideperson + ", chkCareer=" + chkCareer + ", ime_regi_date=" + Arrays.toString(ime_regi_date)
				+ ", ime_exit_date=" + Arrays.toString(ime_exit_date) + ", ime_coname=" + Arrays.toString(ime_coname)
				+ ", ime_auth=" + Arrays.toString(ime_auth) + ", ime_roll=" + Arrays.toString(ime_roll) + "]";
	}
}
