package kr.co.ican.project.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;

//프로젝트 리스트 뿌리기
public class GoProjectAction extends ActionSupport {
	
	private static final long serialVersionUID = -4226295352370749220L;
	private List<ProjectVO> plist; //프로젝트 리스트
	private ProjectVO pvo; //페이징 VO
	private int totalRecordCount; //총 갯수
	private int pageCountPerScreen = 10; // 한 페이지에 뿌릴 리스트의 수
	
	
	public List<ProjectVO> getPlist() {
		return plist;
	}

	public void setPlist(List<ProjectVO> plist) {
		this.plist = plist;
	}

	public ProjectVO getPvo() {
		return pvo;
	}

	public void setPvo(ProjectVO pvo) {
		this.pvo = pvo;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getPageCountPerScreen() {
		return pageCountPerScreen;
	}

	public void setPageCountPerScreen(int pageCountPerScreen) {
		this.pageCountPerScreen = pageCountPerScreen;
	}

	@Override
	public String execute() throws Exception {
		
		ProjectServices pservice = new ProjectServices();
		
		try {
			if(pvo == null){
				pvo = new ProjectVO();
			}
			//service
			totalRecordCount = pservice.getProjectCount();
			plist = pservice.getProjectList(pvo);
			
			//page
			if(totalRecordCount < 0 || plist == null){
				return "error";
			}
			return "success";
			
		} catch (Exception e) {
			return "error";
		}
	}
}

