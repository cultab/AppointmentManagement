/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uniwa.appointmentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author evan
 */
@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient
{
	int id;
	String fullname;
	String address;
	String phone_num;

	public Patient() {
	}

	public Patient(int id, String fullname, String address, String phone_num) {
		this.id = id;
		this.fullname = fullname;
		this.address = address;
		this.phone_num = phone_num;
	}

	public Patient(String fullname, String address, String phone_num) {
		this.id = -1;
		this.fullname = fullname;
		this.address = address;
		this.phone_num = phone_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	
}
