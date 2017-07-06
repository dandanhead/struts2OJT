package kr.co.ican.login.action;

import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.login.service.LoginService;
import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.MemberVO;

// 로그인 
public class LoginMultiAction extends ActionSupport{
	
	private static final long serialVersionUID = -1706257301891741290L;
	
	private MemberVO mvo;
	private String regidate;
	private List<MemberVO> workerlist;
	private List<ProjectVO> plist;
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	
	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public String start() {
			
		return SUCCESS;
																	
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

	public String loginAf()throws Exception{
		//service
		LoginService service = new LoginService();
		WorkerService wservice = new WorkerService();
		ProjectServices pservice = new ProjectServices();
		
		try {
			//Member Check
			mvo = service.checkMember(mvo);
			if(mvo == null){
				return "fail";
			}
			// get Member`s Start Date
			regidate = service.getRegiDate(mvo);
			if(regidate == null){
				return "error";
			}
			// get member list
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
			//make session
			
			ActionContext context = ActionContext.getContext(); //session
			Map<String, Object> session = context.getSession();
			session.put("regidate", regidate);
			session.put("login", mvo);
			context.setSession(session);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
	}
	
	public String logout(){
		
		// session invalidate
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		session.remove("login");
		context.setSession(session);
		
		
		return "success";
	}

}
