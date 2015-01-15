package com.lms.model.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import nl.captcha.Captcha;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import com.lms.helper.DatabaseBusinessDelegate;
import com.lms.model.bean.StudentBean;
import com.lms.exceptions.Message;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware{
	private Map<String, Object> sessionMap;
	private Captcha captcha;
	private StudentBean sb = new StudentBean();
	
	public StudentBean getSb() {
		return sb;
	}

	public void setSb(StudentBean sb) {
		this.sb = sb;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;	
	}

	
	public String execute() throws SQLException{
			return SUCCESS;
	}
	
	@Override
	public void validate(){
		System.out.println("STARTING THIS VALIDATE ON LOGIN ACTION");
		Captcha captcha = (Captcha) sessionMap.get(Captcha.NAME);
		if(captcha.isCorrect(sb.getAnswer())){
			try{
				if(!DatabaseBusinessDelegate.isLoginAccepted(sb, sessionMap)){
					addFieldError("error", "Invalid Student ID/Password");
				}
			} catch (SQLException se){
				se.printStackTrace();
			}
		} else{
			addFieldError("error", "Invalid Captcha Validation.");
		}
		

	}
	


	public Captcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}

}
