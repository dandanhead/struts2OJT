package kr.co.ican.vo;

public class ProjectJoinMemberVO {

	private int ipjl_im_idx; //사번
	private int ipjl_ipl_idx; //프로젝트 번호
	private int ipjl_roll; // roll
	
	
	public ProjectJoinMemberVO() {
	}
	
	public ProjectJoinMemberVO(int ipjl_im_idx, int ipjl_ipl_idx, int ipjl_roll) {
		this.ipjl_im_idx = ipjl_im_idx;
		this.ipjl_ipl_idx = ipjl_ipl_idx;
		this.ipjl_roll = ipjl_roll;
	}

	public int getIpjl_im_idx() {
		return ipjl_im_idx;
	}

	public void setIpjl_im_idx(int ipjl_im_idx) {
		this.ipjl_im_idx = ipjl_im_idx;
	}

	public int getIpjl_ipl_idx() {
		return ipjl_ipl_idx;
	}

	public void setIpjl_ipl_idx(int ipjl_ipl_idx) {
		this.ipjl_ipl_idx = ipjl_ipl_idx;
	}

	public int getIpjl_roll() {
		return ipjl_roll;
	}

	public void setIpjl_roll(int ipjl_roll) {
		this.ipjl_roll = ipjl_roll;
	}

	@Override
	public String toString() {
		return "ProjectJoinMemberVO [ipjl_im_idx=" + ipjl_im_idx + ", ipjl_ipl_idx=" + ipjl_ipl_idx + ", ipjl_roll="
				+ ipjl_roll + "]";
	}
	
}
