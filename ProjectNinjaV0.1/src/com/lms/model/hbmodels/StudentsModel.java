package com.lms.model.hbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="students")
public class StudentsModel {
	
	@Id
	@Column(name="student_id")
	private String student_id;
	
	@Column(name="last_name")
	private String last_name;
	@Column(name="first_name")
	private String first_name;
	@Column(name="middle_initial")
	private String middle_initial;
	@Column(name="year_level")
	private int year_level;
	
	/* Beta version is currently setting this as a normal key.
	 * Change if needed 
	 */
	@Column(name="course_id")
	private String course_id;
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private UserAccountModel user_accounts;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private AddressModel address;

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_initial() {
		return middle_initial;
	}

	public void setMiddle_initial(String middle_initial) {
		this.middle_initial = middle_initial;
	}

	public int getYear_level() {
		return year_level;
	}

	public void setYear_level(int year_level) {
		this.year_level = year_level;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public UserAccountModel getUseracc() {
		return user_accounts;
	}

	public void setUseracc(UserAccountModel user_accounts) {
		this.user_accounts = user_accounts;
	}
	
	
	
	
}
