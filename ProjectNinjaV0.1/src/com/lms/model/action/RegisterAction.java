package com.lms.model.action;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.struts2.interceptor.SessionAware;

import com.lms.utils.UserAssembler;
import com.lms.exceptions.PasswordNotMatchException;
import com.lms.exceptions.StudentIDTakenException;
import com.lms.helper.DatabaseBusinessDelegate;
import com.lms.model.bean.StudentInfoBean;
import com.lms.model.database.Database;
import com.lms.utils.MD5;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport implements SessionAware{
	private Map<String, Object> sessionMap;
	private Connection connection;
	private StudentInfoBean sib = new StudentInfoBean();
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public StudentInfoBean getSib() {
		return sib;
	}

	public void setSib(StudentInfoBean sib) {
		this.sib = sib;
	}
	
	
	public String execute() throws SQLException{
		return SUCCESS;
	}
	
	@Override
	public void validate(){
//		DatabaseBusinessDelegate dbg = DatabaseBusinessDelegate.getInstance();
		Captcha captcha = (Captcha) sessionMap.get(Captcha.NAME);
		System.out.println("hey" + sib.getAnswer());
		if(captcha.isCorrect(sib.getAnswer())){
			try {
//				DatabaseBusinessDelegate.validateRegister(connection, sib);
				String salt= "";
				
				try{
					salt = MD5.getSalt();
				} catch(NoSuchAlgorithmException | NoSuchProviderException e){
					
				}
				sib.setSalt(salt);
				String encryptedPass = MD5.getSecurePassword(sib.getPassword(), sib.getSalt());
				//System.out.println("ENCRYPTEDPASS" + encryptedPass);
				sib.setPassword(encryptedPass);
				DatabaseBusinessDelegate.insertNewUserInfo(sib);

				
//			} catch (PasswordNotMatchException pnme) {
//				addFieldError("error", pnme.getMessage());
//			} catch (StudentIDTakenException sidte) {
//				addFieldError("error", sidte.getMessage());
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
			addFieldError("error", "Invalid Captcha Validation");
		}
	}
	




}
