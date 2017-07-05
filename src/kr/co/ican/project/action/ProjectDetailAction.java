package kr.co.ican.project.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.vo.MemberVO;

public class ProjectDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1688781427833000969L;
	
	private int ipl_idx; //받아온 프로젝트 번호
	private ProjectVO pvo;
	private List<MemberVO> mlist;
	
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
	
	public List<MemberVO> getMlist() {
		return mlist;
	}
	public void setMlist(List<MemberVO> mlist) {
		this.mlist = mlist;
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
			mlist = pservice.
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	
	
}
