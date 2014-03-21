package edu.cs157b.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hospital")
public class Restful {

	RestfulDAO dao = new RestfulDAO();
	
	@GET @Path("doctor")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Doctor> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}

	@GET @Path("doctor/search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Doctor> findByName(@PathParam("query") String query) {
		System.out.println("findBySpecialty: " + query);
		return dao.findBySpecialty(query);
	}

	@GET @Path("doctor/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Doctor findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}

	@POST @Path("doctor/create")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Doctor create(Doctor doctor) {
		System.out.println("creating doctor");
		return dao.create(doctor);
	}

	@PUT @Path("doctor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Doctor Doctor(Doctor doctor) {
		System.out.println("Updating doctor: " + doctor.getName());
		dao.update(doctor);
		return doctor;
	}
	
	@DELETE @Path("doctor/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		dao.remove(id);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@GET @Path("patient")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Patient> findAllP() {
		System.out.println("findAllP");
		return dao.findAllP();
	}


	@GET @Path("patient/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Patient findByIdP(@PathParam("id") String id) {
		System.out.println("findByIdP " + id);
		return dao.findByIdP(Integer.parseInt(id));
	}

	@POST @Path("patient/create")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Patient createP(Patient patient) {
		System.out.println("creating patient");
		return dao.createP(patient);
	}

	@PUT @Path("patient/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Patient Patient(Patient patient) {
		System.out.println("Updating patient: " + patient.getCondition());
		dao.updateP(patient);
		return patient;
	}
	
	@DELETE @Path("patient/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removeP(@PathParam("id") int id) {
		dao.removeP(id);
	}
	
	@GET @Path("patient/search/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Patient> findByRelation(@PathParam("id") String query) {
		System.out.println("findByRelation: " + query);
		return dao.findByRelation(query);
	}
}
