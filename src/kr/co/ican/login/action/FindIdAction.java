package kr.co.ican.login.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.validation.ValidationCheck;
import kr.co.ican.validation.ValidationVO;
import kr.co.ican.worker.vo.MemberVO;

//Find PW
public class FindIdAction extends ActionSupport{
	
	private static final long serialVersionUID = -5569366891733994980L;
	
	private MemberVO mvo; //Worker VO
	private String startnum; // 주민번호 앞자리
	private String endnum; // 주민번호 뒷자리
	
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
		
		try {
			
			//validation
			ValidationCheck val = new ValidationCheck();
			ValidationVO vvo = new ValidationVO();
			vvo = val.findIdValidation(mvo, startnum, endnum);
			// set alertmsg
			if(vvo != null){
				alertmsg = vvo.getMsg();
			}else{
				alertmsg = "error가 발생했습니다. 관리자에게 문의하세요";
			}
			
		} catch (Exception e) {
			return "error";
		}
		
		return "success";
	}

	
	
}
