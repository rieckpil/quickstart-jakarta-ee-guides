package de.rieckpil.blog;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

public class SampleApplicationConfig implements SharedContainerConfiguration {

  @Container
  public static ApplicationContainer app = new ApplicationContainer()
    .withEnv("message", "Hello World from MicroShed Testing")
    .withAppContextRoot("/")
    .withReadinessPath("/health/ready");

}
