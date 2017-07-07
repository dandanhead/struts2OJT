package kr.co.ican.project.action;

import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

//파일 다운로드
public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 6109276538772638275L;

	private String dir; //절대경로
	private String fileName; //파일명
	private InputStream inputStream; //input stream
	
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@Override
	public String execute() throws Exception {
		
		inputStream = new FileInputStream(dir + "/" + fileName);
		return "success";

	}
	
	
	
	
	
}
