package de.rieckpil.quickstarts;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

public class SampleApplicationConfig implements SharedContainerConfiguration {

  @Container
  public static ApplicationContainer app = new ApplicationContainer()
    .withAppContextRoot("/")
    .withReadinessPath("/health/ready");

}
