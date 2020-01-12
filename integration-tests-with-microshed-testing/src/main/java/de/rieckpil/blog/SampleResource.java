package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sample")
@Produces(MediaType.TEXT_PLAIN)
public class SampleResource {

  @Inject
  @ConfigProperty(name = "message")
  private String message;

  @GET
  @Path("/message")
  public String getMessage() {
    return message;
  }

}
