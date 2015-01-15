package com.lms.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lms.exceptions.PasswordNotMatchException;
import com.lms.exceptions.StudentIDTakenException;
import com.lms.model.bean.AddressBean;
import com.lms.model.bean.StudentBean;
import com.lms.model.bean.StudentInfoBean;
import com.lms.model.database.Database;
import com.lms.model.database.SQL;
import com.lms.model.hbmodels.AddressModel;
import com.lms.model.hbmodels.StudentsModel;
import com.lms.model.hbmodels.UserAccountModel;
import com.lms.utils.MD5;

public class DatabaseBusinessDelegate implements SQL{
	private static DatabaseBusinessDelegate dbg = null;
	private static Session sessionHib;
	private static Transaction tx;
	private static SessionFactory conn;
	
	protected DatabaseBusinessDelegate(){
		
	}
	
	public static DatabaseBusinessDelegate getInstance(){
		if (dbg == null){
			dbg = new DatabaseBusinessDelegate();
		}
		return dbg;
	}
	
	public static void validateRegister(Connection conn, StudentBean student) throws PasswordNotMatchException, StudentIDTakenException{
		if(!student.getPassword().equals(student.getConfirmPassword())){
			throw new PasswordNotMatchException();
		} else if(isStudentIDExist(conn, student.getStudentID())){
			throw new StudentIDTakenException();
		}
	}
	
	private static boolean isStudentIDExist(Connection conn, String studentID) {
		boolean exist = false;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = null;
			String user = "";
			String stmt = "SELECT student_id FROM user_accounts";
			rs = s.executeQuery(stmt);
			while (rs.next()) {
				user = rs.getString("student_id");
				if (user.equals(studentID)) {
					exist = true;
					break;
				} else {
					exist = false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	public void insertNewUser(Connection conn, StudentInfoBean student) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(INSERT_NEW_STUDENT_ACCOUNT);
			ps.setString(1, student.getStudentID());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getSalt());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void insertNewUserInfo(StudentInfoBean student) {

//		PreparedStatement ps = null;
//			try {
//			
//			ps = conn.prepareStatement(INSERT_NEW_STUDENT_INFO);
//			ps.setString(1, student.getLastName());
//			ps.setString(2, student.getFirstName());
//			ps.setString(3, student.getMiddleInitial());
//			ps.setString(4, student.getStudentID());
//			ps.setString(5, student.getYearLevel());
//			ps.setString(6, student.getCourseID());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		conn = Database.getSessionFactory();
		Session session = conn.openSession();
		tx = session.beginTransaction();
		
		try{
			StudentsModel stud = new StudentsModel();
			stud.setLast_name(student.getLastName());
			stud.setFirst_name(student.getFirstName());
			stud.setMiddle_initial(student.getMiddleInitial());
			stud.setStudent_id(student.getStudentID());
			stud.setYear_level(student.getYearLevel());
			stud.setCourse_id(student.getCourseID());
			
			AddressModel studAddress = new AddressModel();
			studAddress.setAddress_id(1);
			studAddress.setStreet(student.getStreet());
			System.out.println("BARANGAY: " + student.getBarangay());
			studAddress.setBarangay(student.getBarangay());
			studAddress.setCity(student.getCity());
			studAddress.setProvince(student.getProvince());
			
			UserAccountModel user = new UserAccountModel();
			user.setAccount_id(1);
			user.setPassword(student.getPassword());
			user.setSalt(student.getSalt());
			
			
			studAddress.setStudent(stud);
			user.setStudentacc(stud);
			stud.setAddress(studAddress);
			stud.setUseracc(user);
			
			session.save(studAddress);
			session.save(user);
			session.save(stud);
			
			tx.commit();
			System.out.println("HOOMAN SAVED YAY!");
			session.flush();
			session.clear();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertAddress(Connection conn, StudentInfoBean student){
		PreparedStatement ps = null;
		
		try{
		
			ps = conn.prepareStatement(INSERT_ADDRESS);
			ps.setString(1, student.getStreet());
			ps.setString(2, student.getBarangay());
			ps.setString(3, student.getCity());
			ps.setString(4, student.getProvince());
			ps.setString(5, student.getStudentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public UserAccountModel getUser(SessionFactory conn) throws SQLException {
//		conn = Database.getSessionFactory();
//		sessionHib = conn.openSession();
//		tx = sessionHib.beginTransaction();
//		String all = "*";
//		String table = "user";
//		String column = "admin";
//		String condition = "NULL";
//
////		ps = conn.prepareStatement(SELECT_USER);
////
////		rs = ps.executeQuery();
////		return rs;
//		
//		try{
//			Query query = sessionHib.createSQLQuery(SELECT_USER);
//			List results = query.list();
//			
//			if(results.isEmpty()){
//				sessionHib.flush();
//				sessionHib.clear();
//				sessionHib.close();
////				return ;
//			}
//		}catch(Exception e){
//			System.err.println(e);
//		}
//	}
	
	
	public static boolean isLoginAccepted(StudentBean user, Map<String, Object> sessionMap) throws SQLException {
		boolean isAccepted = false;
		String encryptedPassword = "";
		
		conn = Database.getSessionFactory();
		sessionHib = conn.openSession();
		tx = sessionHib.beginTransaction();
		System.out.println(encryptedPassword);
		

			System.out.println(user.getStudentID());
			try{
				Query query = sessionHib.createSQLQuery(SELECT_USER);
				query.setParameter("thisID", user.getStudentID());
				List results = query.list();
				
				if(results.isEmpty()){
					sessionHib.flush();
					sessionHib.clear();
					sessionHib.close();
					isAccepted = false;
				}
				
				for(Iterator listIterator = results.iterator();listIterator.hasNext();){
		            Object[] values = (Object[]) listIterator.next();
		            encryptedPassword = MD5.getSecurePassword(user.getPassword(),(String) values[1]);
		            for(int i=0;i<values.length;i++){
						if (values[0].equals(encryptedPassword)) {
								sessionMap.put("currentUser", user.getStudentID());
								sessionMap.put("user_details", user);
						        tx.commit();
						        sessionHib.flush();
						        sessionHib.clear();
						        isAccepted = true;
			            }
		            }	
				}
			}catch(Exception e){
				System.err.println(e);
			}			
//			if (studentID.equals(rs.getString("student_id"))
//					&& encryptedPassword.equals(rs.getString("password"))) {
//				isAccepted = true;
//			}
		

		return isAccepted;
	}
}
