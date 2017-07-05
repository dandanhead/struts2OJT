package kr.co.ican.project.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectJoinMemListVO;
import kr.co.ican.project.vo.ProjectVO;

public class ProjectDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1688781427833000969L;
	
	private int ipl_idx; //받아온 프로젝트 번호
	private ProjectVO pvo; //페이지 구현시 페이징넘버 setting
	private List<ProjectJoinMemListVO> pjlist;
	
	
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
	public List<ProjectJoinMemListVO> getPjlist() {
		return pjlist;
	}
	public void setPjlist(List<ProjectJoinMemListVO> pjlist) {
		this.pjlist = pjlist;
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
			if(pvo == null){
				return "error";
			}
			pjlist = pservice.getProjectJoinMembers(pvo);
			if(pjlist == null){
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	
	
}
