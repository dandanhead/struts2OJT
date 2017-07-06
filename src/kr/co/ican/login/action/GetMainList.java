package kr.co.ican.login.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.MemberVO;

public class GetMainList extends ActionSupport {

	
	private static final long serialVersionUID = -4140290836851734795L;
	
	private MemberVO mvo;
	private List<MemberVO> workerlist;
	private List<ProjectVO> plist;
	
	
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
			workerlist = wservice.getWorkerList(mvo);
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
