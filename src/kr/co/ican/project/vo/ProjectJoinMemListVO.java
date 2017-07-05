package kr.co.ican.project.vo;

public class ProjectJoinMemListVO {

//	IM_IDX, IM_NAME, IM_SKILL, IM_STATUS,  YEAR#, MONTH# 
	private int im_idx;
	private String im_dname;
	private String im_phone;
	private String im_name;
	private String im_email;
	private int im_status;
	private int expYear;
	private int expMonth;
	
	public ProjectJoinMemListVO() {
		
	}

	public int getIm_idx() {
		return im_idx;
	}

	public void setIm_idx(int im_idx) {
		this.im_idx = im_idx;
	}

	public String getIm_dname() {
		return im_dname;
	}

	public void setIm_dname(String im_dname) {
		this.im_dname = im_dname;
	}

	public String getIm_phone() {
		return im_phone;
	}

	public void setIm_phone(String im_phone) {
		this.im_phone = im_phone;
	}

	public String getIm_name() {
		return im_name;
	}

	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}

	public String getIm_email() {
		return im_email;
	}

	public void setIm_email(String im_email) {
		this.im_email = im_email;
	}

	public int getIm_status() {
		return im_status;
	}

	public void setIm_status(int im_status) {
		this.im_status = im_status;
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

	@Override
	public String toString() {
		return "ProjectJoinMemListVO [im_idx=" + im_idx + ", im_dname=" + im_dname + ", im_phone=" + im_phone
				+ ", im_name=" + im_name + ", im_email=" + im_email + ", im_status=" + im_status + ", expYear="
				+ expYear + ", expMonth=" + expMonth + "]";
	}
	
}
