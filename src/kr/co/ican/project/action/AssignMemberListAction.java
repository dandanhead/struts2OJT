package kr.co.ican.project.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.AssignMemberVO;
public class AssignMemberListAction extends ActionSupport {

	private static final long serialVersionUID = -3856817760324080305L;
	private List<AssignMemberVO> aslist;
	private AssignMemberVO asvo;
	private int pageNumber;
	private int ipl_idx;
	
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
}
