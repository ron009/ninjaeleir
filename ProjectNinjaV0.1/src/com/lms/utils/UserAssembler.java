package com.lms.utils;

import com.lms.model.bean.*;

public class UserAssembler {
	
	public static StudentInfoBean getInstance(String student_id, String firstName, String lastName,String middleInitial,
			String street, String barangay, String city, String province, int yearLevel, String courseID, String password, String confirmPassword, String cptans) {
		
		StudentInfoBean user = new StudentInfoBean();
		user.setStudentID(student_id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMiddleInitial(middleInitial);
		user.setStreet(street);
		user.setBarangay(barangay);
		user.setCity(city);
		user.setProvince(province);
		user.setYearLevel(yearLevel);
		user.setCourseID(courseID);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		user.setAnswer(cptans);
		
		return user;
		
	}

}
