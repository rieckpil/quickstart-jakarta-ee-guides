package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  @Inject
  @ConfigProperty(name = "message")
  private String message;

  @GET
  @Path("/message")
  public JsonObject getMessage() {
    return Json.createObjectBuilder().add("message", message).build();
  }

}
