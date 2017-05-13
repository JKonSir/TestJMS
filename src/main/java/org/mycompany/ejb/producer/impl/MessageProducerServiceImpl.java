package org.mycompany.ejb.producer.impl;

import org.mycompany.ejb.producer.MessageProducerService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

@Stateless(mappedName = "ejb/MessageProducerServiceImpl")
public class MessageProducerServiceImpl implements MessageProducerService {

    @Inject
    @JMSConnectionFactory(value = "java:comp/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    @Override
    public void produceMessage(String message) {
        try {
            jmsContext.createProducer().send(queue, message);
        } catch (JMSRuntimeException ex) {

        }
    }

}
