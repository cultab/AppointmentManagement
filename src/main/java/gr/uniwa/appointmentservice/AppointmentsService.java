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

//	@Context
//	private UriInfo context;

	/**
	 * Creates a new instance of AppointmentsService
	 */
	public AppointmentsService() {
	}

	/**
	 * Saves an Appointment object
	 *
	 * @param app the appointment object to create
	 */
	@POST
	@Path("/appointments")
	@Consumes(MediaType.APPLICATION_XML)
	public void createAppointment(Appointment app) {
		int res;
		AppointmentDao dao = new AppointmentDao();

		res = dao.create(app);

		if (res == 0) {
			throw new WebApplicationException(201);
		} else {
			throw new WebApplicationException(409);
		}
	}

	/**
	 * Retrieves representation of an instance of
	 * gr.uniwa.appointmentservice.AppointmentsService
	 *
	 * @param id id of appointment requested
	 *
	 * @return an instance of Appointment
	 */

	@GET
	@Path("/appointment/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Appointment getAppointment(@PathParam("id") int id) {
		AppointmentDao dao = new AppointmentDao();
		Appointment app = dao.retrieve(id);

		if (app != null) {
			return app;
		} else {
			throw new WebApplicationException(404);
		}
	}

	@GET
	@Path("/appointments")
	@Produces(MediaType.APPLICATION_XML)
	public List<Appointment> getAllAppointments() {
		AppointmentDao dao = new AppointmentDao();
		return dao.retrieveAll();
	}

	@PUT
	@Path("/appointment/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateAppointment(@PathParam("id") int id, Appointment app) {
		int res;
		AppointmentDao dao = new AppointmentDao();

		res = dao.update(id, app);

		if (res == 0) {
				return;
		} else {
				throw new WebApplicationException(404); //not found
		}
	}

	@DELETE
	@Path("/appointment/{id}")
	public void deleteAppointment(@PathParam("id") int id) {
		int res;
		AppointmentDao dao = new AppointmentDao();

		res = dao.delete(id);

		if (res == 0) {
				return;
		} else {
				throw new WebApplicationException(404); //not found
		}
	}
}
