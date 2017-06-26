package kr.co.ican.login.action;

import java.io.IOException;
import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.login.service.LoginService;
import kr.co.ican.worker.vo.MemberVO;


//Find PW
public class FindIdAction extends ActionSupport{
	
	
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
			
			String im_scnum = startnum + "-" + endnum;
			mvo.setIm_scnum(im_scnum);
			mvo = service.findId(mvo);
			
			if(mvo == null){
				addFieldError("mvoIsNull", "등록된 정보가 없습니다.");
				return "fail";
			}else{
				
				return "success";
			}
			
		}else{
			return "fail";
		}
	}
	
	//validation
	private boolean isvalidator() throws IOException{
		boolean flag =true;
		
		//1. 이름
		//2. 주민번호
		if(!isblank()){
					
			addFieldError("im_name", "이름은 공백일 수 없습니다.");
			flag = false;
		}
		if(!isScnumblank()){
			addFieldError("im_scnum", "주민번호 13자리를 정확히 입력해 주세요.");
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
	
	
}
