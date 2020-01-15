/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uniwa.appointmentservice;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author evan
 */

@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment implements Serializable
{

	private static final long serialVersionUID = 1L;

	int id;
	int patient_id;
	int doctor_id;
	int room_id;
	String date;
	String time;

	public Appointment() {
	}

	public Appointment(int patient_id, int doctor_id, int room_id, String date, String time) {
		this.id = -1;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.room_id = room_id;
		this.date = date;
		this.time = time;
	}

	public Appointment(int id, int patient_id, int doctor_id, int room_id, String date, String time) {
		this.id = id;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.room_id = room_id;
		this.date = date;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
