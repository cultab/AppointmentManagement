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
@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room
{
	int id;
	String description;

	public Room() {
	}

	public Room(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Room(String description) {
		this.id = -1;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
