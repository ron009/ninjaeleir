package com.lms.model.hbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="address")
public class AddressModel {
	@Id
	@Column(name="address_id")
	private int address_id;
	@Column(name="street")
	private String street;
	@Column(name="barangay")
	private String barangay;
	@Column(name="city")
	private String city;
	@Column(name="province")
	private String province;
	
	@OneToOne(mappedBy="address")
	private StudentsModel student;

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public StudentsModel getStudent() {
		return student;
	}

	public void setStudent(StudentsModel student) {
		this.student = student;
	}
	
	/*
	 * Currently setting it to transient until "Professor's" Classes are finished *Pun unintended
	 */
//	@Transient
//	private String prof_id;
	
	
	
	
}
