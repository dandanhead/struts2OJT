package kr.co.ican.worker.action;

import com.opensymphony.xwork2.ActionSupport;

public class DetailWorkerAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private int idx;
	private String expy;
	private String expm;
	
	private int totalRecordCount; // 총 인원수 
	private int pageCountPerScreen = 5; // 한 페이지당 보여줄 인원 수
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getExpy() {
		return expy;
	}
	public void setExpy(String expy) {
		this.expy = expy;
	}
	public String getExpm() {
		return expm;
	}
	public void setExpm(String expm) {
		this.expm = expm;
	}
	@Override
	public String execute() throws Exception {
		
//		System.out.println("idx : " + idx + " , expy : " + expy + " , expm : " + expm);
		
		return "";
	}
	
	
	
}
