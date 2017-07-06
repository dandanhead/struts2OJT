package kr.co.ican.project.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;

public class DeleteProjectAction extends ActionSupport {

	private static final long serialVersionUID = 6442487104518037758L;
	private int ipl_idx;
	
	public int getIpl_idx() {
		return ipl_idx;
	}
	public void setIpl_idx(int ipl_idx) {
		this.ipl_idx = ipl_idx;
	}
	@Override
	public String execute() throws Exception {
		
		ProjectServices pservice = new ProjectServices();
		boolean result = false;
		try {
			result = pservice.deleteProject(ipl_idx);
			if(result == false){
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
	}
	
	
	
	
	
}
