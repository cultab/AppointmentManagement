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

	@GET
	@Path("/appointments")
	@Produces(MediaType.APPLICATION_XML)
	public List<Appointment> getAllAppointments() {
		return dao.retrieveAllAppointments();
	}

	@GET
	@Path("/appointment/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Appointment getAppointment(@PathParam("id") int id) {
		Appointment app = dao.retrieveAppointment(id);

		if (app != null) {
			return app;
		} else {
			throw new WebApplicationException(404);
		}
	}

	@PUT
	@Path("/appointment/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateAppointment(@PathParam("id") int id, Appointment app) {
		int res;
		res = dao.updateAppointment(id, app);

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
		res = dao.deleteAppointment(id);

		if (res == 0) {
			return;
		} else {
			throw new WebApplicationException(404); //not found
		}
	}

}
