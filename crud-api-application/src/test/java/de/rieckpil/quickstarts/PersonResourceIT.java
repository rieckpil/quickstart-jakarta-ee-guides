package de.rieckpil.quickstarts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class PersonResourceIT {

  @RESTClient
  public static PersonResource personsEndpoint;

  @AfterEach
  public void cleanUp() {
    for (Person person : personsEndpoint.getAllPersons().readEntity(new GenericType<List<Person>>() {
    })) {
      personsEndpoint.deletePerson(person.getId());
    }
  }

  @Test
  public void shouldReturnAllPersons() {

    personsEndpoint.createNewPerson(null, new PersonCreationRequest(1L, "duke", "jakarta"));
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(2L, "duke", "jakarta"));
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(3L, "duke", "jakarta"));

    Response allPersons = personsEndpoint.getAllPersons();

    assertEquals(3, allPersons.readEntity(new GenericType<List<Person>>() {
    }).size());
  }

  @Test
  public void shouldCreateNewPerson() {
    Long randomId = ThreadLocalRandom.current().nextLong(1, 1_000_000);
    Response response = personsEndpoint.createNewPerson(null, new PersonCreationRequest(randomId, "duke", "jakarta"));

    URI locationHeader = response.getLocation();

    assertTrue(locationHeader.toString().contains(randomId.toString()), "Should contain the Primary Key of the Person");
    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

    Response personById = personsEndpoint.getPersonById(randomId);

    assertNotNull(personById.readEntity(Person.class));
  }

  @Test
  public void shouldUpdatePerson() {
    Long randomId = ThreadLocalRandom.current().nextLong(1, 1_000_000);
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(randomId, "duke", "jakarta"));
    personsEndpoint.updatePerson(randomId, new PersonUpdateRequest("foo", "bar"));

    Person personById = personsEndpoint.getPersonById(randomId).readEntity(Person.class);

    assertEquals("foo", personById.getFirstName());
    assertEquals("bar", personById.getLastName());
  }

  @Test
  public void shouldDeletePerson() {
    Long randomId = ThreadLocalRandom.current().nextLong(1, 1_000_000);
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(randomId, "duke", "jakarta"));
    Response personById = personsEndpoint.getPersonById(randomId);
    assertEquals(Response.Status.OK.getStatusCode(), personById.getStatus());

    personsEndpoint.deletePerson(randomId);
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), personsEndpoint.getPersonById(randomId).getStatus());
  }

}
