package kr.co.ican.vo;

public class MemLicenseVO {

    private int iml_im_idx;
	private String iml_lname;
	private String iml_acqdate;
	private String iml_organization;
	
	public MemLicenseVO() {
	}
	
	public MemLicenseVO(int iml_im_idx, String iml_lname, String iml_acqdate, String iml_organization) {
		this.iml_im_idx = iml_im_idx;
		this.iml_lname = iml_lname;
		this.iml_acqdate = iml_acqdate;
		this.iml_organization = iml_organization;
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

	@Override
	public String toString() {
		return "MemLicenseVO [iml_im_idx=" + iml_im_idx + ", iml_lname=" + iml_lname + ", iml_acqdate=" + iml_acqdate
				+ ", iml_organization=" + iml_organization + "]";
	}
}
