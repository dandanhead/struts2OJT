package kr.co.ican.project.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.AssignMemberVO;
import kr.co.ican.project.vo.ProjectVO;

// 프로젝트에 사원을 추가 하기 위한 사원 리스트 가져오기
public class AssignMemberListAction extends ActionSupport {

	private static final long serialVersionUID = -3856817760324080305L;
	private List<AssignMemberVO> aslist; //사원 리스트
	private AssignMemberVO asvo; //리스트 paging 을 위한 vo
	private int pageNumber; //페이지 번호
	private int ipl_idx; //프로젝트 번호
	private ProjectVO pvo; 
	
	public List<AssignMemberVO> getAslist() {
		return aslist;
	}
	public void setAslist(List<AssignMemberVO> aslist) {
		this.aslist = aslist;
	}
	public AssignMemberVO getAsvo() {
		return asvo;
	}
	public void setAsvo(AssignMemberVO asvo) {
		this.asvo = asvo;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
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
		
		ProjectServices pservice = new ProjectServices();
		
		if(asvo == null){
			asvo = new AssignMemberVO();
		}
		
		try {
			
			aslist = pservice.getAssignMemList(asvo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
	}
	
	public String getRemoveMember() throws Exception{
		ProjectServices pservice = new ProjectServices();
		
		if(pvo == null){
			pvo = new ProjectVO();
		}
		
		//vo setting
		pvo.setIpl_idx(ipl_idx);
		
		try {
			
			aslist = pservice.getRemoveMemList(pvo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
}
