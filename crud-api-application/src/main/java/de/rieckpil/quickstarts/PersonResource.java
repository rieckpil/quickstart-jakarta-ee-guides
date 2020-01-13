package de.rieckpil.quickstarts;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/persons")
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

  @PersistenceContext
  private EntityManager entityManager;

  @GET
  public Response getAllPersons() {
    return Response.ok(entityManager.createNamedQuery("Person.findAll", Person.class).getResultList()).build();
  }

  @GET
  @Path("/{id}")
  public Response getPersonById(@PathParam("id") Long id) {

    Person personById = entityManager.find(Person.class, id);

    if (personById == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok(personById).build();
  }

  @POST
  public Response createNewPerson(@Context UriInfo uriInfo, @Valid PersonCreationRequest personCreationRequest) {

    if (entityManager.find(Person.class, personCreationRequest.getId()) != null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    Person person = new Person(personCreationRequest);
    entityManager.persist(person);

    URI uri = uriInfo.getAbsolutePathBuilder()
      .path(personCreationRequest.getId().toString())
      .build();

    return Response.created(uri).build();
  }

  @PUT
  @Path("/{id}")
  public Response updatePerson(@PathParam("id") Long id, @Valid PersonUpdateRequest personUpdateRequest) {

    Person personToUpdate = entityManager.find(Person.class, id);

    if (personToUpdate == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    personToUpdate.setLastName(personUpdateRequest.getLastName());
    personToUpdate.setFirstName(personUpdateRequest.getFirstName());

    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response deletePerson(@PathParam("id") Long id) {
    Person personToDelete = entityManager.find(Person.class, id);

    if (personToDelete != null) {
      entityManager.remove(personToDelete);
    }

    return Response.ok().build();
  }

}
