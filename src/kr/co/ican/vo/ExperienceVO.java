package kr.co.ican.vo;

public class ExperienceVO {
	
	private int ime_im_idx;
	private String ime_regi_date;
	private String ime_exit_date;
	private String ime_coname;
	private int ime_auth;
	private String ime_roll;
	
	// 페이징
	private int recordCountPerPage = 5;
	private int pageNumber = 0;
	private int start = 1;
	private int end = 5;
	
	public ExperienceVO() {
	}

	public ExperienceVO(int ime_im_idx, String ime_regi_date, String ime_exit_date, String ime_coname, int ime_auth,
			String ime_roll, int recordCountPerPage, int pageNumber, int start, int end) {
		super();
		this.ime_im_idx = ime_im_idx;
		this.ime_regi_date = ime_regi_date;
		this.ime_exit_date = ime_exit_date;
		this.ime_coname = ime_coname;
		this.ime_auth = ime_auth;
		this.ime_roll = ime_roll;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	
	public String getIme_roll() {
		return ime_roll;
	}

	public void setIme_roll(String ime_roll) {
		this.ime_roll = ime_roll;
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

	public int getIme_im_idx() {
		return ime_im_idx;
	}

	public void setIme_im_idx(int ime_im_idx) {
		this.ime_im_idx = ime_im_idx;
	}

	public String getIme_regi_date() {
		return ime_regi_date;
	}

	public void setIme_regi_date(String ime_regi_date) {
		this.ime_regi_date = ime_regi_date;
	}

	public String getIme_exit_date() {
		return ime_exit_date;
	}

	public void setIme_exit_date(String ime_exit_date) {
		this.ime_exit_date = ime_exit_date;
	}

	public String getIme_coname() {
		return ime_coname;
	}

	public void setIme_coname(String ime_coname) {
		this.ime_coname = ime_coname;
	}

	public int getIme_auth() {
		return ime_auth;
	}

	public void setIme_auth(int ime_auth) {
		this.ime_auth = ime_auth;
	}

	@Override
	public String toString() {
		return "ExperienceVO [ime_im_idx=" + ime_im_idx + ", ime_regi_date=" + ime_regi_date + ", ime_exit_date="
				+ ime_exit_date + ", ime_coname=" + ime_coname + ", ime_auth=" + ime_auth + ", ime_roll=" + ime_roll
				+ ", recordCountPerPage=" + recordCountPerPage + ", pageNumber=" + pageNumber + ", start=" + start
				+ ", end=" + end + "]";
	}
}
