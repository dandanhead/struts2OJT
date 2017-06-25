package kr.co.ican.vo;

public class MemberVO {
	
	private int im_idx;
	private String im_pw;
	private String im_dname;
	private String im_name;
	private String im_phone;
	private String im_email;
	private int im_resign;
	private int im_status;
	private String im_scnum;
	private String im_address;
	private String im_detailaddr;
	private String im_postcode;
	private int im_auth;
	private String im_skill;
	//검색
	private String s_category; 
	private String s_keyword;
	// 페이징
	private int recordCountPerPage = 15;
	private int pageNumber = 0;
	private int start = 1;
	private int end = 15;
	//경력
	private int expYear;
	private int expMonth;
	//외부업체 컬럼
	private String outsideperson;
	//입사일
	private String ime_regi_date;
	public MemberVO() {}

	
	
	public MemberVO(int im_idx, String im_pw) {
		super();
		this.im_idx = im_idx;
		this.im_pw = im_pw;
	}

	

	public MemberVO(int im_idx, String im_pw, String im_dname, String im_name, String im_phone, String im_email,
			int im_resign, int im_status, String im_scnum, String im_address, String im_detailaddr, String im_postcode,
			int im_auth, String im_skill, String s_category, String s_keyword, int recordCountPerPage, int pageNumber,
			int start, int end, int expYear, int expMonth, String outsideperson) {
		this.im_idx = im_idx;
		this.im_pw = im_pw;
		this.im_dname = im_dname;
		this.im_name = im_name;
		this.im_phone = im_phone;
		this.im_email = im_email;
		this.im_resign = im_resign;
		this.im_status = im_status;
		this.im_scnum = im_scnum;
		this.im_address = im_address;
		this.im_detailaddr = im_detailaddr;
		this.im_postcode = im_postcode;
		this.im_auth = im_auth;
		this.im_skill = im_skill;
		this.s_category = s_category;
		this.s_keyword = s_keyword;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
		this.expYear = expYear;
		this.expMonth = expMonth;
		this.outsideperson = outsideperson;
	}

	public String getIm_skill() {
		return im_skill;
	}

	public void setIm_skill(String im_skill) {
		this.im_skill = im_skill;
	}

	public String getIm_detailaddr() {
		return im_detailaddr;
	}
	public void setIm_detailaddr(String im_detailaddr) {
		this.im_detailaddr = im_detailaddr;
	}
	public int getIm_idx() {
		return im_idx;
	}

	public void setIm_idx(int im_idx) {
		this.im_idx = im_idx;
	}

	public String getIm_pw() {
		return im_pw;
	}

	public void setIm_pw(String im_pw) {
		this.im_pw = im_pw;
	}

	public String getIm_dname() {
		return im_dname;
	}

	public void setIm_dname(String im_dname) {
		this.im_dname = im_dname;
	}

	public String getIm_name() {
		return im_name;
	}

	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}

	public String getIm_phone() {
		return im_phone;
	}

	public void setIm_phone(String im_phone) {
		this.im_phone = im_phone;
	}

	public String getIm_email() {
		return im_email;
	}

	public void setIm_email(String im_email) {
		this.im_email = im_email;
	}

	public int getIm_resign() {
		return im_resign;
	}

	public void setIm_resign(int im_resign) {
		this.im_resign = im_resign;
	}

	public int getIm_status() {
		return im_status;
	}

	public void setIm_status(int im_status) {
		this.im_status = im_status;
	}

	public String getIm_scnum() {
		return im_scnum;
	}

	public void setIm_scnum(String im_scnum) {
		this.im_scnum = im_scnum;
	}

	public String getIm_address() {
		return im_address;
	}

	public void setIm_address(String im_address) {
		this.im_address = im_address;
	}

	public String getIm_postcode() {
		return im_postcode;
	}

	public void setIm_postcode(String im_postcode) {
		this.im_postcode = im_postcode;
	}

	public int getIm_auth() {
		return im_auth;
	}

	public void setIm_auth(int im_auth) {
		this.im_auth = im_auth;
	}

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getS_keyword() {
		return s_keyword;
	}

	public void setS_keyword(String s_keyword) {
		this.s_keyword = s_keyword;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}



	public String getOutsideperson() {
		return outsideperson;
	}



	public void setOutsideperson(String outsideperson) {
		this.outsideperson = outsideperson;
	}

	
	public int getExpYear() {
		return expYear;
	}



	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}



	public int getExpMonth() {
		return expMonth;
	}



	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	
	public String getIme_regi_date() {
		return ime_regi_date;
	}



	public void setIme_regi_date(String ime_regi_date) {
		this.ime_regi_date = ime_regi_date;
	}



	@Override
	public String toString() {
		return "MemberVO [im_idx=" + im_idx + ", im_pw=" + im_pw + ", im_dname=" + im_dname + ", im_name=" + im_name
				+ ", im_phone=" + im_phone + ", im_email=" + im_email + ", im_resign=" + im_resign + ", im_status="
				+ im_status + ", im_scnum=" + im_scnum + ", im_address=" + im_address + ", im_detailaddr="
				+ im_detailaddr + ", im_postcode=" + im_postcode + ", im_auth=" + im_auth + ", im_skill=" + im_skill
				+ ", s_category=" + s_category + ", s_keyword=" + s_keyword + ", recordCountPerPage="
				+ recordCountPerPage + ", pageNumber=" + pageNumber + ", start=" + start + ", end=" + end + ", expYear="
				+ expYear + ", expMonth=" + expMonth + ", outsideperson=" + outsideperson + ", ime_regi_date="
				+ ime_regi_date + "]";
	}
	
}
