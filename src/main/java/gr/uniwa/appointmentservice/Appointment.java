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
	String patient;
	String doctor;
	String room;
	String date;
	String time;

	public Appointment() {
	}

	public Appointment(String patient, String doctor, String room, String date, String time) {
		this.id = -1;
		this.patient = patient;
		this.doctor = doctor;
		this.room = room;
		this.date = date;
		this.time = time;
	}

	public Appointment(int id, String patient, String doctor, String room, String date, String time) {
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.room = room;
		this.date = date;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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
