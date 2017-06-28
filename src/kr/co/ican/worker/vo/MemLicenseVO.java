package kr.co.ican.worker.vo;


// 사원 자격증 관련 VO
public class MemLicenseVO {

    private int iml_im_idx;
	private String iml_lname;
	private String iml_acqdate;
	private String iml_organization;
	
	// 페이징
	private int recordCountPerPage = 5;
	private int pageNumber = 0;
	private int start = 1;
	private int end = 5;
	
	public MemLicenseVO() {
	}
	
	public String getIml_acqdate() {
		return iml_acqdate;
	}

	public void setIml_acqdate(String iml_acqdate) {
		this.iml_acqdate = iml_acqdate;
	}

	public String getIml_organization() {
		return iml_organization;
	}

	public void setIml_organization(String iml_organization) {
		this.iml_organization = iml_organization;
	}

	public int getIml_im_idx() {
		return iml_im_idx;
	}
	public void setIml_im_idx(int iml_im_idx) {
		this.iml_im_idx = iml_im_idx;
	}
	public String getIml_lname() {
		return iml_lname;
	}
	public void setIml_lname(String iml_lname) {
		this.iml_lname = iml_lname;
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

	@Override
	public String toString() {
		return "MemLicenseVO [iml_im_idx=" + iml_im_idx + ", iml_lname=" + iml_lname + ", iml_acqdate=" + iml_acqdate
				+ ", iml_organization=" + iml_organization + ", recordCountPerPage=" + recordCountPerPage
				+ ", pageNumber=" + pageNumber + ", start=" + start + ", end=" + end + "]";
	}
}
