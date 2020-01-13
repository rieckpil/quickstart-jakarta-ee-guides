package de.rieckpil.quickstarts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class PersonCreationRequest {

  @Positive
  private Long id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  public PersonCreationRequest() {
  }

  public PersonCreationRequest(Long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
