package de.rieckpil.quickstarts;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/persons")
@ApplicationScoped
@Transactional(TxType.REQUIRED)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

  @PersistenceContext
  private EntityManager entityManager;

  @GET
  public List<Person> getAllPersons() {
    return entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
  }

  @GET
  @Path("/{id}")
  public Response getPersonById(@PathParam("id") Long id) {
    var personById = entityManager.find(Person.class, id);

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

    var person = new Person(personCreationRequest);
    entityManager.persist(person);

    var headerLocation = uriInfo.getAbsolutePathBuilder()
      .path(person.getId().toString())
      .build();

    return Response.created(headerLocation).build();
  }

  @PUT
  @Path("/{id}")
  public Response updatePerson(@PathParam("id") Long id, @Valid PersonUpdateRequest personUpdateRequest) {

    var personToUpdate = entityManager.find(Person.class, id);

    if (personToUpdate == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    personToUpdate.setFirstName(personUpdateRequest.getFirstName());
    personToUpdate.setLastName(personUpdateRequest.getLastName());

    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response deletePersonById(@PathParam("id") Long id) {
    var personToDelete = entityManager.find(Person.class, id);

    if (personToDelete != null) {
      entityManager.remove(personToDelete);
    }

    return Response.ok().build();
  }
}
