package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.Instant;

@Singleton
public class JmsMessageSender {

  @Resource(lookup = "jms/JmsFactory")
  private ConnectionFactory jmsFactory;

  @Resource(lookup = "jms/JmsQueue")
  private Queue jmsQueue;

  private Connection connection;
  private Session session;
  private MessageProducer producer;

  @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
  public void sendJmsMessage() {
    System.out.println("Sending a new message");
    this.send();
  }

  @PostConstruct
  public void init() {
    try {
      this.connection = jmsFactory.createConnection();
      this.session = connection.createSession();
      this.producer = session.createProducer(jmsQueue);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  private void send() {
    try {
      TextMessage message = session.createTextMessage();
      message.setText(createCustomMessage());
      producer.send(message);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  private String createCustomMessage() {
    CustomMessage msg = new CustomMessage(1L, "Hello World!", "Duke", Instant.now().getEpochSecond());
    Jsonb jsonb = JsonbBuilder.create();
    return jsonb.toJson(msg);
  }
}
