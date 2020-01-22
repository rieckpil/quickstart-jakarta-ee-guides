package de.rieckpil.blog;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("quotes")
public class QuoteResources {

  @Inject
  @RestClient
  private QuoteRestClient quoteRestClient;

  @GET
  public String getRandomQuote() {
    var quoteOfTheDayPointer = Json.createPointer("/contents/quotes/0/quote");
    var quoteOfTheDay = quoteOfTheDayPointer.getValue(quoteRestClient.getQuoteOfTheDay()).toString();
    return quoteOfTheDay;
  }
}
