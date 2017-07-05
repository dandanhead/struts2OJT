package kr.co.ican.project.vo;

import java.util.Arrays;

public class AddAssignMemberVO {
	
	private int chkvalues[]; //사번
	private int rolls[]; //역할
	private int ipl_idx; //프로젝트 번호
	
	
	public AddAssignMemberVO() {
		
	}
	
	public int[] getChkvalues() {
		return chkvalues;
	}
	public void setChkvalues(int[] chkvalues) {
		this.chkvalues = chkvalues;
	}
	public int[] getRolls() {
		return rolls;
	}
	public void setRolls(int[] rolls) {
		this.rolls = rolls;
	}
	public int getIpl_idx() {
		return ipl_idx;
	}
	public void setIpl_idx(int ipl_idx) {
		this.ipl_idx = ipl_idx;
	}

	@Override
	public String toString() {
		return "AddAssignMemberVO [chkvalues=" + Arrays.toString(chkvalues) + ", rolls=" + Arrays.toString(rolls)
				+ ", ipl_idx=" + ipl_idx + "]";
	}
	
}
