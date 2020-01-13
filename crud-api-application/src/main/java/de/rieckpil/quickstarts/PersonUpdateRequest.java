package de.rieckpil.quickstarts;

import javax.validation.constraints.NotEmpty;

public class PersonUpdateRequest {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  public PersonUpdateRequest() {
  }

  public PersonUpdateRequest(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
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
