package kr.co.ican.worker.action;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.worker.service.WorkerService;

public class ResignWorkerAction extends ActionSupport {

	private static final long serialVersionUID = 7157959051774240025L;
	private int idx;
	private int resultchk;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResultchk() {
		return resultchk;
	}

	public void setResultchk(int resultchk) {
		this.resultchk = resultchk;
	}

	@Override
	public String execute() throws Exception {
		
		WorkerService wservice = new WorkerService();
		boolean isResult = false;
		try {
			isResult = wservice.doReignWorker(idx);
			if(isResult ==false){
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
}
