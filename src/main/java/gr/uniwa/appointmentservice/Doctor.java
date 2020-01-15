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
@XmlRootElement(name = "doctor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor
{
	int id;
	int spec_id;
	String fullname;

	public Doctor() {
	}

	public Doctor(int id, int spec_id, String full_name) {
		this.id = id;
		this.spec_id = spec_id;
		this.fullname = full_name;
	}

	public Doctor(int spec_id, String full_name) {
		this.id = -1;
		this.spec_id = spec_id;
		this.fullname = full_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String full_name) {
		this.fullname = full_name;
	}
	
	
}
