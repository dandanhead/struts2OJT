package kr.co.ican.login.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.MemberVO;
// 상위 메뉴 홈버튼 클릭시  메인페이지로 이동
public class GetMainList extends ActionSupport {
	
	private static final long serialVersionUID = -4140290836851734795L;
	
	private MemberVO mvo; // worker VO
	private List<MemberVO> workerlist; // Worker List
	private List<ProjectVO> plist; // Project List
	
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}


	public List<MemberVO> getWorkerlist() {
		return workerlist;
	}


	public void setWorkerlist(List<MemberVO> workerlist) {
		this.workerlist = workerlist;
	}


	public List<ProjectVO> getPlist() {
		return plist;
	}


	public void setPlist(List<ProjectVO> plist) {
		this.plist = plist;
	}


	@Override
	public String execute() throws Exception {
		
		WorkerService wservice = new WorkerService();
		ProjectServices pservice = new ProjectServices();
		
		try {
			// get member list
			if(mvo == null){
				mvo = new MemberVO();
			}
			workerlist = wservice.getWorkerList(mvo); //사원 리스트
			if(workerlist == null){
				return "error";
			}
			// get project list
			ProjectVO pvo = new ProjectVO();
			plist = pservice.getProjectList(pvo);
			if(plist == null){
				return "error";
			}
			
		} catch (Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
		
	}

	
	
	
	
	
}
