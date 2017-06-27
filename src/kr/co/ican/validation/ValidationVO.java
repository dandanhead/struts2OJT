package kr.co.ican.validation;

public class ValidationVO {

	private boolean resultfalg;
	private String msg;
	
	public ValidationVO() {
		
	}

	public boolean isResultfalg() {
		return resultfalg;
	}

	public void setResultfalg(boolean resultfalg) {
		this.resultfalg = resultfalg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ValidationVO [resultfalg=" + resultfalg + ", msg=" + msg + "]";
	}
	
	

}
