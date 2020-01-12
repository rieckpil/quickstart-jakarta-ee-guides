package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class SampleResourceIT {

  @RESTClient
  public static SampleResource sampleEndpoint;

  @Test
  public void shouldReturnSampleMessage() {
    assertEquals("Hello World from MicroShed Testing",
      sampleEndpoint.getMessage());
  }

}
