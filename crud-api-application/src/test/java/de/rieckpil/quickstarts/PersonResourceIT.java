package de.rieckpil.quickstarts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class PersonResourceIT {

  @RESTClient
  public static PersonResource personsEndpoint;

  @AfterEach
  public void cleanUp() {
    for (Person person : personsEndpoint.getAllPersons()) {
      personsEndpoint.deletePersonById(person.getId());
    }
  }

  @Test
  public void shouldCreatePerson() {
    PersonCreationRequest duke = new PersonCreationRequest(1337L, "duke", "jakarta");
    Response result = personsEndpoint.createNewPerson(null, duke);

    assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
    var createdUrl = result.getHeaderString("Location");
    assertNotNull(createdUrl);

    var newPerson = personsEndpoint.getPersonById(duke.getId()).readEntity(Person.class);
    assertNotNull(newPerson);
    assertEquals("duke", newPerson.getFirstName());
    assertEquals("jakarta", newPerson.getLastName());
  }

  @Test
  public void shouldUpdatePerson() {
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(42L, "duke", "jakarta"));
    personsEndpoint.updatePerson(42L, new PersonUpdateRequest("duke", "foo"));

    var updatedDuke = personsEndpoint.getPersonById(42L).readEntity(Person.class);

    assertEquals("duke", updatedDuke.getFirstName());
    assertEquals("foo", updatedDuke.getLastName());
  }

  @Test
  public void shouldDeletePerson() {
    personsEndpoint.createNewPerson(null, new PersonCreationRequest(13L, "duke", "jakarta"));
    personsEndpoint.deletePersonById(13L);

    var response = personsEndpoint.getPersonById(14L);

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
