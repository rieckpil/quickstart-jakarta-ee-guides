package de.rieckpil.blog;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@MessageDriven
public class JmsMessageReader implements MessageListener {

  @Override
  public void onMessage(Message message) {
    try {
      TextMessage textMessage = (TextMessage) message;
      String incomingTextMessage = textMessage.getText();
      System.out.println("--- a new message arrived: " + incomingTextMessage);
      System.out.println("--- parsed message with Jsonb: " + JsonbBuilder.create().fromJson(incomingTextMessage, CustomMessage.class));
    } catch (JMSException e) {
      System.err.println(e.getMessage());
    }
  }
}
