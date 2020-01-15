/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uniwa.appointmentservice;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * REST Web Service
 *
 * @author evan
 */
@Path("/service")
public class AppointmentsService
{

	Dao dao;
//	@Context
//	private UriInfo context;

	public AppointmentsService() {
		dao = new Dao();
	}
	
	// CREATE
	@POST
	@Path("/appointments")
	@Consumes(MediaType.APPLICATION_XML)
	public void createAppointment(Appointment app) {
		int res;

		res = dao.createAppointment(app);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	// RETRIEVE
	@GET
	@Path("/appointments")
	@Produces(MediaType.APPLICATION_XML)
	public List<Appointment> getAppointments(
			@QueryParam("patient_id") @DefaultValue("-1") int patient_id,
			@QueryParam("doctor_id")  @DefaultValue("-1") int doctor_id,
			@QueryParam("room_id")    @DefaultValue("-1") int room_id) {
		
		return dao.retrieveAppointments(patient_id, doctor_id, room_id);
	}

	// RETRIVE
	@GET
	@Path("/appointment/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Appointment getAppointment(@PathParam("id") int id) {
		Appointment app = dao.retrieveAppointment(id);

		if (app == null) {
			throw new WebApplicationException(404);
		}

		return app;
	}

	// UPDATE
	@PUT
	@Path("/appointment/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateAppointment(@PathParam("id") int id, Appointment app) {
		int res;
		res = dao.updateAppointment(id, app);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// DELETE
	@DELETE
	@Path("/appointment/{id}")
	public void deleteAppointment(@PathParam("id") int id) {
		int res;
		res = dao.deleteAppointment(id);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// CREATE
	@POST
	@Path("/patients")
	@Consumes(MediaType.APPLICATION_XML)
	public void createPatient(Patient patient) {
		int res;

		res = dao.createPatient(patient);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	// RETRIEVE
	@GET
	@Path("/patients")
	@Produces(MediaType.APPLICATION_XML)
	public List<Patient> getAllPatients() {
		return dao.retrieveAllPatients();
	}

	// RETRIVE
	@GET
	@Path("/patient/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Patient getPatient(@PathParam("id") int id) {
		Patient patient = dao.retrievePatient(id);

		if (patient == null) {
			throw new WebApplicationException(404);
		}

		return patient;
	}

	// UPDATE
	@PUT
	@Path("/patient/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updatePatient(@PathParam("id") int id, Patient patient) {
		int res;
		res = dao.updatePatient(id, patient);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// DELETE
	@DELETE
	@Path("/patient/{id}")
	public void deletePatient(@PathParam("id") int id) {
		int res;
		res = dao.deletePatient(id);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// CREATE
	@POST
	@Path("/doctors")
	@Consumes(MediaType.APPLICATION_XML)
	public void createPatient(Doctor doc) {
		int res;

		res = dao.createDoctor(doc);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	// RETRIEVE
	@GET
	@Path("/doctors")
	@Produces(MediaType.APPLICATION_XML)
	public List<Doctor> getAllDoctors(
		@QueryParam("spec_id") @DefaultValue("-1") int spec_id) {
		return dao.retrieveDoctors(spec_id);
	}

	// RETRIVE
	@GET
	@Path("/doctor/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Doctor getDoctor(@PathParam("id") int id) {
		Doctor doc = dao.retrieveDoctor(id);

		if (doc == null) {
			throw new WebApplicationException(404);
		}

		return doc;
	}

	// UPDATE
	@PUT
	@Path("/doctor/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateDoctor(@PathParam("id") int id, Doctor doc) {
		int res;
		res = dao.updateDoctor(id, doc);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// DELETE
	@DELETE
	@Path("/doctor/{id}")
	public void deleteDoctor(@PathParam("id") int id) {
		int res;
		res = dao.deleteDoctor(id);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// CREATE
	@POST
	@Path("/rooms")
	@Consumes(MediaType.APPLICATION_XML)
	public void createRoom(Room room) {
		int res;

		res = dao.createRoom(room);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	// RETRIEVE
	@GET
	@Path("/rooms")
	@Produces(MediaType.APPLICATION_XML)
	public List<Room> getAllRooms() {
		return dao.retrieveAllRooms();
	}

	// RETRIVE
	@GET
	@Path("/room/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Room getRoom(@PathParam("id") int id) {
		Room room = dao.retrieveRoom(id);

		if (room == null) {
			throw new WebApplicationException(404);
		} 

		return room;
	}

	// UPDATE
	@PUT
	@Path("/room/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateRoom(@PathParam("id") int id, Room room) {
		int res;
		res = dao.updateRoom(id, room);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// DELETE
	@DELETE
	@Path("/room/{id}")
	public void deleteRoom(@PathParam("id") int id) {
		int res;
		res = dao.deleteRoom(id);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// CREATE
	@POST
	@Path("/specialities")
	@Consumes(MediaType.APPLICATION_XML)
	public void createSpeciality(Speciality spec) {
		int res;

		res = dao.createSpeciality(spec);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	// RETRIEVE
	@GET
	@Path("/specialities")
	@Produces(MediaType.APPLICATION_XML)
	public List<Speciality> getAllSpecialitys() {
		return dao.retrieveAllSpecialities();
	}

	// RETRIVE
	@GET
	@Path("/specialitiy/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Speciality getSpeciality(@PathParam("id") int id) {
		Speciality spec = dao.retrieveSpeciality(id);

		if (spec == null) {
			throw new WebApplicationException(404);
		} 

		return spec;
	}

	// UPDATE
	@PUT
	@Path("/speciality/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateSpeciality(@PathParam("id") int id, Speciality spec) {
		int res;
		res = dao.updateSpeciallity(id, spec);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}

	// DELETE
	@DELETE
	@Path("/speciality/{id}")
	public void deleteSpeciality(@PathParam("id") int id) {
		int res;
		res = dao.deleteSpeciality(id);

		if (res != 0) {
			throw new WebApplicationException(404); //not found
		}
	}
}
