package kr.co.ican.project.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;

public class ProjectDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1688781427833000969L;
	
	private int ipl_idx;
	private ProjectVO pvo;
	
	public int getIpl_idx() {
		return ipl_idx;
	}
	public void setIpl_idx(int ipl_idx) {
		this.ipl_idx = ipl_idx;
	}
	public ProjectVO getPvo() {
		return pvo;
	}
	public void setPvo(ProjectVO pvo) {
		this.pvo = pvo;
	}
	@Override
	public String execute() throws Exception {
		// 프로젝트 상세정보 가져오기
		ProjectServices pservice = new ProjectServices();
		//vo setting
		if(pvo == null){
			pvo = new ProjectVO();
		}
		pvo.setIpl_idx(ipl_idx);
		
		try {
			
			pvo = pservice.getProjectDetail(pvo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	
	
}
