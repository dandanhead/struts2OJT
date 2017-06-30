package kr.co.ican.worker.vo;

import java.util.List;

public class UpdateWorkerVO {

	private int idx;
	private MemberVO mvo;
	private MemLicenseVO licvo;
	private ExperienceVO evo;
	private List<MemLicenseVO> liclist;
	private List<ExperienceVO> elist;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public MemberVO getMvo() {
		return mvo;
	}
	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	public MemLicenseVO getLicvo() {
		return licvo;
	}
	public void setLicvo(MemLicenseVO licvo) {
		this.licvo = licvo;
	}
	public ExperienceVO getEvo() {
		return evo;
	}
	public void setEvo(ExperienceVO evo) {
		this.evo = evo;
	}
	public List<MemLicenseVO> getLiclist() {
		return liclist;
	}
	public void setLiclist(List<MemLicenseVO> liclist) {
		this.liclist = liclist;
	}
	public List<ExperienceVO> getElist() {
		return elist;
	}
	public void setElist(List<ExperienceVO> elist) {
		this.elist = elist;
	}
	
	@Override
	public String toString() {
		return "UpdateWorkerVO [idx=" + idx + ", mvo=" + mvo + ", licvo=" + licvo + ", evo=" + evo + ", liclist="
				+ liclist + ", elist=" + elist + "]";
	}
	
	
}
