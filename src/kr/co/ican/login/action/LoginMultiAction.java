package kr.co.ican.login.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.login.service.LoginService;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemberVO;

// 로그인 
public class LoginMultiAction extends ActionSupport{
	
	private static final long serialVersionUID = -1706257301891741290L;
	
	private MemberVO mvo;
	private ExperienceVO evo;

	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	
	public ExperienceVO getEvo() {
		return evo;
	}

	public void setEvo(ExperienceVO evo) {
		this.evo = evo;
	}

	 public String start() {
			
		return SUCCESS;
																	
	}
	
	public String loginAf(){
		//service
		LoginService service = new LoginService();
		
		//Member Check
		mvo = service.checkMember(mvo);
		
		if(mvo != null){
			// get Member`s Start Date
			evo = service.getMemberStartDate(mvo);
			// make session
			if(evo != null){
				ActionContext context = ActionContext.getContext(); //session
				Map<String, Object> session = context.getSession();
				session.put("evo", evo);
				session.put("login", mvo);
				context.setSession(session);
				
				return "success";
			}else{
				
				return "fail";
			}
		
		}else{
			return "fail";
		}
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
