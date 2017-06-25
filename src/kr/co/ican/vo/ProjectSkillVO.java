package kr.co.ican.vo;

public class ProjectSkillVO {

	private String ips_is_sname; // 스킬명
	private int ips_ipl_idx; //프로젝트 번호
	
	public ProjectSkillVO() {
	}

	public ProjectSkillVO(String ips_is_sname, int ips_ipl_idx) {
		this.ips_is_sname = ips_is_sname;
		this.ips_ipl_idx = ips_ipl_idx;
	}

	public String getIps_is_sname() {
		return ips_is_sname;
	}

	public void setIps_is_sname(String ips_is_sname) {
		this.ips_is_sname = ips_is_sname;
	}

	public int getIps_ipl_idx() {
		return ips_ipl_idx;
	}

	public void setIps_ipl_idx(int ips_ipl_idx) {
		this.ips_ipl_idx = ips_ipl_idx;
	}

	@Override
	public String toString() {
		return "ProjectSkillVO [ips_is_sname=" + ips_is_sname + ", ips_ipl_idx=" + ips_ipl_idx + "]";
	}
	
}
