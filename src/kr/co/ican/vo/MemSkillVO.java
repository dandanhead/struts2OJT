package kr.co.ican.vo;

public class MemSkillVO {

	private String ims_is_sname;
	private  int ims_im_idx;
	
	public MemSkillVO() {
		
	}

	public MemSkillVO(String ims_is_sname, int ims_im_idx) {
		super();
		this.ims_is_sname = ims_is_sname;
		this.ims_im_idx = ims_im_idx;
	}

	public String getIms_is_sname() {
		return ims_is_sname;
	}

	public void setIms_is_sname(String ims_is_sname) {
		this.ims_is_sname = ims_is_sname;
	}

	public int getIms_im_idx() {
		return ims_im_idx;
	}

	public void setIms_im_idx(int ims_im_idx) {
		this.ims_im_idx = ims_im_idx;
	}

	@Override
	public String toString() {
		return "MemSkillVO [ims_is_sname=" + ims_is_sname + ", ims_im_idx=" + ims_im_idx + "]";
	}

	
	
	
	
}
