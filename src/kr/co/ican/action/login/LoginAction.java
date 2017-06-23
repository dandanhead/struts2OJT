package kr.co.ican.action.login;

import com.opensymphony.xwork2.Action;

import kr.co.ican.vo.MemberVO;

public class LoginAction implements Action{
	
	private MemberVO mvo;
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	@Override
	public String execute() throws Exception {
		
		System.out.println("im_idx : " + mvo.getIm_idx() );
		System.out.println("im_pw :  " + mvo.getIm_pw());
		
		return "ok";
	}
}
