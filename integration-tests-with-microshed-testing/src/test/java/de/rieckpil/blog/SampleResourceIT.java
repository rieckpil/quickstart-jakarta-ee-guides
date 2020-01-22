package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class SampleResourceIT {

  @RESTClient
  public static SampleResource sampleResource;

  @Test
  public void shouldReturnSampleMessage() {
    var response = sampleResource.getMessage();
    System.out.println(response);
    assertNotNull(response);
    assertEquals("Hello World from MicroShed Testing!", response.getString("message"));
  }

  @Test
  public void shouldReturnDatabaseInformation() {
    var response = sampleResource.getDatabaseInformation();

    assertNotNull(response);
    System.out.println(response);
  }

}
