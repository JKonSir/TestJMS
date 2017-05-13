package org.mycompany.service;

import org.mycompany.ejb.consumer.RegisterConsumerService;
import org.mycompany.ejb.producer.MessageProducerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by konstantinamp on 5/10/17.
 */
@Service
public class JMSService {

    private final MessageProducerService jmsTestProducer;
    private final RegisterConsumerService jmsTestConsumer;


    @Inject
    public JMSService(@Named(value = "registerConsumerService") RegisterConsumerService jmsTestConsumer,
                      @Named(value = "messageProducerService") MessageProducerService jmsTestProducer) {
        this.jmsTestProducer = jmsTestProducer;
        this.jmsTestConsumer = jmsTestConsumer;
    }

    @PostConstruct
    public void registerConsumer() {
        jmsTestConsumer.registerConsumer();
    }

    public void testMethodService(String message) {
        jmsTestProducer.produceMessage(message);
    }

}
