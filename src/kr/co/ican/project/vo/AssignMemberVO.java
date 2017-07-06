package kr.co.ican.project.vo;

public class AssignMemberVO {
	
	//이름, 스킬
	private int im_idx;
	private String im_name;
	private String im_skill;
	private int im_status;
	// 경력
	private int expYear;
	private int expMonth;
	// 더보기 
	private int pageNumber = 0;
	private int start = 1;
	private int end = 10;
	
	public AssignMemberVO() {
		
	}
	public int getIm_idx() {
		return im_idx;
	}
	public void setIm_idx(int im_idx) {
		this.im_idx = im_idx;
	}
	public String getIm_name() {
		return im_name;
	}
	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}
	public String getIm_skill() {
		return im_skill;
	}
	public void setIm_skill(String im_skill) {
		this.im_skill = im_skill;
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
	public int getIm_status() {
		return im_status;
	}
	public void setIm_status(int im_status) {
		this.im_status = im_status;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	@Override
	public String toString() {
		return "AssignMemberVO [im_idx=" + im_idx + ", im_name=" + im_name + ", im_skill=" + im_skill + ", im_status="
				+ im_status + ", expYear=" + expYear + ", expMonth=" + expMonth + ", pageNumber=" + pageNumber
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
