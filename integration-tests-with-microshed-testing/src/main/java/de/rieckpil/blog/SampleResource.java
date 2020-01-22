package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;

@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  @Inject
  @ConfigProperty(name = "message")
  private String message;

  @Resource(lookup = "jdbc/postgresql")
  private DataSource dataSource;

  @GET
  @Path("/message")
  public JsonObject getMessage() {
    return Json.createObjectBuilder().add("message", message).build();
  }

  @GET
  @Path("/database")
  public JsonObject getDatabaseInformation() {
    try (Connection con = dataSource.getConnection()) {
      return Json.createObjectBuilder()
        .add("databaseName", con.getMetaData().getDatabaseProductName())
        .add("versionMajor", con.getMetaData().getDatabaseMajorVersion())
        .add("versionMinor", con.getMetaData().getDatabaseMinorVersion())
        .build();
    } catch (SQLException e) {
      return Json.createObjectBuilder().add("message", "error").build();
    }
  }
}
