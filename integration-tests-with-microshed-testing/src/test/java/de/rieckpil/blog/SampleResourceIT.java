package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicroShedTest
public class SampleResourceIT {

  @Container
  public static ApplicationContainer app = new ApplicationContainer()
    .withEnv("message", "Hello World from MicroShed Testing")
    .withAppContextRoot("/")
    .withReadinessPath("/health/ready");

  @RESTClient
  public static SampleResource sampleEndpoint;

  @Test
  public void shouldReturnSampleMessage() {
    assertEquals("Hello World from MicroShed Testing",
      sampleEndpoint.getMessage());
  }

}
