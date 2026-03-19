
// AI-assisted code generated with Claude (Anthropic)
// Prompt: "Create a JMS Producer class called IsbnRequestProducer in package app.mq
// that uses JmsTemplate to send a book ID as a message to a queue defined in
// application.properties under the key llm.queue.name."
package app.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class IsbnRequestProducer {
    private final JmsTemplate jmsTemplate;

    @Value("${llm.queue.name}")
    private String destination;

    public IsbnRequestProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String sendRequest(String message) {
        System.out.println("Producer sending to queue: " + message);
        jmsTemplate.convertAndSend(destination, message);
        return "Request accepted";
    }
}
