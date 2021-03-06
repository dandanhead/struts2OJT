package kr.co.ican.project.vo;

public class ProjectVO {

	private int ipl_idx; //프로젝트 번호
	private String ipl_pname; //프로젝트명
	private String ipl_sdate; //프로젝트 시작일
	private String ipl_eptdate; //프로젝트 예상 종료일
	private String ipl_edate; //프로젝트 종료일
	private String ipl_content; // 프로젝트 상세설명
	private String ipl_doc; //프로젝트 문서파일명
	private String ipl_charge; //프로젝트 책임자
	private String ipl_client; //고객사
	private String ipl_address; //프로젝트 서비스 지역
	private String ipl_detailaddr; //상세주소
	private String ipl_postcode; // 우편번호
	private String ipl_skill; //프로젝트 스킬
	private int ipl_del;
	
	// 페이징
	private int recordCountPerPage = 10;
	private int pageNumber = 0;
	private int start = 1;
	private int end = 15;
	
	public ProjectVO() {
	}
	
	public String getIpl_skill() {
		return ipl_skill;
	}

	public void setIpl_skill(String ipl_skill) {
		this.ipl_skill = ipl_skill;
	}

	public int getIpl_idx() {
		return ipl_idx;
	}

	public void setIpl_idx(int ipl_idx) {
		this.ipl_idx = ipl_idx;
	}

	public String getIpl_pname() {
		return ipl_pname;
	}

	public void setIpl_pname(String ipl_pname) {
		this.ipl_pname = ipl_pname;
	}

	public String getIpl_sdate() {
		return ipl_sdate;
	}

	public void setIpl_sdate(String ipl_sdate) {
		this.ipl_sdate = ipl_sdate;
	}

	public String getIpl_eptdate() {
		return ipl_eptdate;
	}

	public void setIpl_eptdate(String ipl_eptdate) {
		this.ipl_eptdate = ipl_eptdate;
	}

	public String getIpl_edate() {
		return ipl_edate;
	}

	public void setIpl_edate(String ipl_edate) {
		this.ipl_edate = ipl_edate;
	}

	public String getIpl_content() {
		return ipl_content;
	}

	public void setIpl_content(String ipl_content) {
		this.ipl_content = ipl_content;
	}

	public String getIpl_doc() {
		return ipl_doc;
	}

	public void setIpl_doc(String ipl_doc) {
		this.ipl_doc = ipl_doc;
	}

	public String getIpl_charge() {
		return ipl_charge;
	}

	public void setIpl_charge(String ipl_charge) {
		this.ipl_charge = ipl_charge;
	}

	public String getIpl_client() {
		return ipl_client;
	}

	public void setIpl_client(String ipl_client) {
		this.ipl_client = ipl_client;
	}

	public String getIpl_address() {
		return ipl_address;
	}

	public void setIpl_address(String ipl_address) {
		this.ipl_address = ipl_address;
	}

	public String getIpl_detailaddr() {
		return ipl_detailaddr;
	}

	public void setIpl_detailaddr(String ipl_detailaddr) {
		this.ipl_detailaddr = ipl_detailaddr;
	}

	public String getIpl_postcode() {
		return ipl_postcode;
	}

	public void setIpl_postcode(String ipl_postcode) {
		this.ipl_postcode = ipl_postcode;
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
	
	public int getIpl_del() {
		return ipl_del;
	}

	public void setIpl_del(int ipl_del) {
		this.ipl_del = ipl_del;
	}

	@Override
	public String toString() {
		return "ProjectVO [ipl_idx=" + ipl_idx + ", ipl_pname=" + ipl_pname + ", ipl_sdate=" + ipl_sdate
				+ ", ipl_eptdate=" + ipl_eptdate + ", ipl_edate=" + ipl_edate + ", ipl_content=" + ipl_content
				+ ", ipl_doc=" + ipl_doc + ", ipl_charge=" + ipl_charge + ", ipl_client=" + ipl_client
				+ ", ipl_address=" + ipl_address + ", ipl_detailaddr=" + ipl_detailaddr + ", ipl_postcode="
				+ ipl_postcode + ", ipl_skill=" + ipl_skill + ", ipl_del=" + ipl_del + ", recordCountPerPage="
				+ recordCountPerPage + ", pageNumber=" + pageNumber + ", start=" + start + ", end=" + end + "]";
	}
}
