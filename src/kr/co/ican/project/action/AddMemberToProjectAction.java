package kr.co.ican.project.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.AddAssignMemberVO;

public class AddMemberToProjectAction extends ActionSupport {

	
	private static final long serialVersionUID = 5344323873248400861L;
	
	private int chkvalues[];
	private int rolls[];
	private int insertCheck;
	private int ipl_idx;
	public int[] getChkvalues() {
		return chkvalues;
	}

	public void setChkvalues(int[] chkvalues) {
		this.chkvalues = chkvalues;
	}
	public int getInsertCheck() {
		return insertCheck;
	}
	
	public void setInsertCheck(int insertCheck) {
		this.insertCheck = insertCheck;
	}
	public int getIpl_idx() {
		return ipl_idx;
	}

	public void setIpl_idx(int ipl_idx) {
		this.ipl_idx = ipl_idx;
	}
	public int[] getRolls() {
		return rolls;
	}

	public void setRolls(int[] rolls) {
		this.rolls = rolls;
	}

	@Override
	public String execute() throws Exception {
		ProjectServices pservice = new ProjectServices();
		AddAssignMemberVO aavo = new AddAssignMemberVO();
		boolean resultCheck = false;
		
		try {
			// vo setting
			aavo.setChkvalues(chkvalues);
			aavo.setIpl_idx(ipl_idx);
			aavo.setRolls(rolls);
			
			//call service
			resultCheck = pservice.addAssignMember(aavo);
			if(resultCheck == false){
				return "error";
			}
			
			//winddow close param
			insertCheck += 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	public String removeMember() throws Exception{
		ProjectServices pservice = new ProjectServices();
		AddAssignMemberVO aavo = new AddAssignMemberVO();
		boolean resultCheck = false;
		
		try {
			// vo setting
			aavo.setChkvalues(chkvalues);
			aavo.setIpl_idx(ipl_idx);
			aavo.setRolls(rolls);
			
			//call service
			resultCheck = pservice.removeAssignMember(aavo);
			if(resultCheck == false){
				return "error";
			}
			
			//winddow close param
			insertCheck += 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
}
