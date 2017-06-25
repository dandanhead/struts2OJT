package kr.co.ican.action.login;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import kr.co.ican.service.login.LoginServiceImpl;
import kr.co.ican.service.login.LoginServices;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

// 로그인 
public class LoginMultiAction implements Action{
	
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

	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
		
	}
	
	public String loginAf() throws Exception{
		//service
		LoginServices service = new LoginServiceImpl();
		
		//Member Check
		mvo = service.checkMember(mvo);
		
		if(mvo != null){
			// get Member`s Start Date
			evo = service.getMemberStartDate(mvo);
			
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
	
	 public String start() throws Exception{
		
		return SUCCESS;
																	
	}
	 
	public String logout() throws Exception{
		
		// session invalidate
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		session.remove("login");
		context.setSession(session);
		
		
		return "success";
	}

}
