package kr.co.ican.login.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.login.service.LoginService;
import kr.co.ican.worker.vo.MemberVO;


//Find ID
public class FindPwAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private MemberVO mvo;
	private int startnum;
	private int endnum;
	
	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	public int getStartnum() {
		return startnum;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	public int getEndnum() {
		return endnum;
	}

	public void setEndnum(int endnum) {
		this.endnum = endnum;
	}

	@Override
	public String execute() throws Exception {
		if(isvalidator()){
			
			LoginService service = new LoginService();
			String im_scnum = startnum + "-"+endnum;
			mvo.setIm_scnum(im_scnum);
			
			mvo = service.findPw(mvo);
			
			if(mvo == null){
				addFieldError("mvoIsNull", "등록된 정보가 없습니다.");
				return "fail";
			}else{
				addFieldError("successmessage", "찾으시는 비밀번호는 : " + mvo.getIm_pw() + " 입니다.");
				return "success";
			}
			
		}else{
			return "fail";
		}
	}
	
	
	// validation
	private boolean isvalidator(){
		boolean flag =true;
		//1. 이름
		//2. 주민번호
		//3. mail check
		if(!isblank()){
			addFieldError("im_name", "이름은 공백일 수 없습니다.");
			flag = false;
		}
		if(!isScnumblank()){
			addFieldError("im_scnum", "주민번호 13자리를 정확히 입력해 주세요.");
			flag = false;
		}
		if(!isMailCheck()){
			addFieldError("im_email", "이메일 주소는 공백일 수 없습니다.");
			flag = false;
		}
		if(!isMailFormCheck()){
			addFieldError("im_email", "이메일 주소 형식이 아닙니다.");
			flag = false;
		}
		
		return flag;
		
	}
	
	// name null check
	private boolean isblank(){
		
		String username = mvo.getIm_name().trim();
		
		if(username == null || ("").equals(username)){
			
			return false;
		}else{
			return true;
		}
		
	}
	
	// social number null check
	private boolean isScnumblank(){
		
		if(startnum == 0 || endnum == 0){
			
			return false;
		}else{
			return true;
		}
	}
	
	// email null check
	private boolean isMailCheck(){
		String usermail = mvo.getIm_email().trim();
		
		if(usermail == null || ("").equals(usermail)){
			return false;
		}else{
			return true;
		}
		
	}
	
	// eamil form check
	private boolean isMailFormCheck(){
		String usermail = mvo.getIm_email().trim();
		if(usermail.indexOf("@") == -1){
			return false;
		}else{
			return true;
		}
	}
	
}
