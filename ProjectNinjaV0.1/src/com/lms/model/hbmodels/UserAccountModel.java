package com.lms.model.hbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_accounts")
public class UserAccountModel {
	@Id
	@Column(name="account_id")
	private int account_id;
//	private String student_id;
	@Column(name="password")
	private String password;
	@Column(name="salt")
	private String salt;
//	private String prof_id;
	
	@OneToOne(mappedBy="user_accounts")
	private StudentsModel studentacc;

//	public String getStudent_id() {
//		return student_id;
//	}
//
//	public void setStudent_id(String student_id) {
//		this.student_id = student_id;
//	}

	public String getPassword() {
		return password;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public StudentsModel getStudentacc() {
		return studentacc;
	}

	public void setStudentacc(StudentsModel studentacc) {
		this.studentacc = studentacc;
	}
	
	
}
