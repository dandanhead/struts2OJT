package kr.co.ican.login.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.validation.ValidationCheck;
import kr.co.ican.validation.ValidationVO;
import kr.co.ican.worker.vo.MemberVO;


//Find ID
public class FindPwAction extends ActionSupport{
	
	private static final long serialVersionUID = 7934989377337423285L;
	
	private MemberVO mvo;
	private String startnum;
	private String endnum;
	
	// alert message
	private String alertmsg;
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	
	public String getStartnum() {
		return startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getAlertmsg() {
		return alertmsg;
	}

	public void setAlertmsg(String alertmsg) {
		this.alertmsg = alertmsg;
	}

	@Override
	public String execute() throws Exception {
		ValidationCheck val = new ValidationCheck();
		ValidationVO vvo = new ValidationVO();
		
		vvo = val.findPwValidation(mvo, startnum, endnum);
		
		if(vvo != null){
			alertmsg = vvo.getMsg();
		}else{
			alertmsg = "error 가 발생했습니다. 관리자에게 문의하세요";
		}
		
		return "success";
	}
	
}
