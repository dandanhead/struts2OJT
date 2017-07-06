package kr.co.ican.project.action;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.project.service.ProjectServices;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.validation.ValidationCheck;
import kr.co.ican.validation.ValidationVO;

public class AddProjectAfAction extends ActionSupport {

	private static final long serialVersionUID = 1306705384897279716L;
	
	// file upload
	private static final String FilePath = "D://tempupload";
	private File ipl_doc;
	private String ipl_docContentType;
	private String ipl_docFileName;
	File saveFile;
	
	// project Info
	private ProjectVO pvo;
	//error message
	private String errMessage;
	
	
	public ProjectVO getPvo() {
		return pvo;
	}
	public void setPvo(ProjectVO pvo) {
		this.pvo = pvo;
	}
	
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public File getIpl_doc() {
		return ipl_doc;
	}
	public void setIpl_doc(File ipl_doc) {
		this.ipl_doc = ipl_doc;
	}
	public String getIpl_docContentType() {
		return ipl_docContentType;
	}
	public void setIpl_docContentType(String ipl_docContentType) {
		this.ipl_docContentType = ipl_docContentType;
	}
	public String getIpl_docFileName() {
		return ipl_docFileName;
	}
	public void setIpl_docFileName(String ipl_docFileName) {
		this.ipl_docFileName = ipl_docFileName;
	}
	@Override
	public String execute() throws Exception {
		
		ValidationVO vvo = new ValidationVO();
		ValidationCheck check = new ValidationCheck();
		ProjectServices pservice = new ProjectServices();
		boolean insertCheck = false;
		
		try {
			//1.validation
			vvo = check.addProjectValidation(pvo);
			
			if(vvo.isResultfalg() == true){
				
				//2.DB insert
				pvo.setIpl_doc(ipl_docFileName);
				
				insertCheck =  pservice.addProject(pvo);
				
				if(insertCheck == false){
					return "error";
				}
				
				//3.file Upload
				if(ipl_doc != null && ipl_doc.exists()){
					saveFile = new File(FilePath, ipl_docFileName);
					
					if(saveFile == null){
						return "error";
					}
					//upload
					FileUtils.copyFile(ipl_doc, saveFile);
				}
				
			}else{
				errMessage = vvo.getMsg();
				return "fail";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
	}
}
