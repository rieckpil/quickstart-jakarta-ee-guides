package de.rieckpil.blog;

import com.google.common.net.MediaType;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import javax.json.Json;
import javax.json.JsonObject;

import static de.rieckpil.blog.SampleApplicationConfig.mockServer;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class QuoteResourcesIT {

  @RESTClient
  public static QuoteResources quoteResources;

  @Test
  public void shouldReturnRandomQuote() {

    JsonObject resultQuote = Json.createObjectBuilder()
      .add("contents",
        Json.createObjectBuilder().add("quotes",
          Json.createArrayBuilder().add(Json.createObjectBuilder()
            .add("quote", "Do not worry if you have built your castles in the air. " +
              "They are where they should be. Now put the foundations under them."))))
      .build();

    new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort())
      .when(HttpRequest.request("/qod"))
      .respond(HttpResponse.response().withBody(resultQuote.toString(), MediaType.JSON_UTF_8));

    String randomQuote = quoteResources.getRandomQuote();

    assertNotNull(randomQuote);
    System.out.println(randomQuote);
  }
}
