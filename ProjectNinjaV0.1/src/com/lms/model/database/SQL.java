package com.lms.model.database;

public interface SQL {
	String DB_SOURCE = "java:comp/env/jdbc/thesis";
	
	String INSERT_NEW_STUDENT_INFO = "INSERT into students(last_name, first_name, middle_initial, student_id, year_level, course_id)"
			+"VALUES (?,?,?,?,?,?)";
	
	String INSERT_NEW_STUDENT_ACCOUNT = "INSERT into user_accounts(student_id,password, salt) VALUES(?,?,?)";

	String INSERT_ADDRESS = "INSERT into address(street, barangay, city, province,student_id) VALUE(?,?,?,?,?)";

	String SELECT_USER = "select * from user_accounts where account_id=(select account_id from students where student_id= :thisID)";
}
