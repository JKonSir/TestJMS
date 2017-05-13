package org.mycompany.ejb.producer.impl;

import org.mycompany.ejb.producer.MessageProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

@Stateless(mappedName = "ejb/MessageProducerServiceImpl")
public class MessageProducerServiceImpl implements MessageProducerService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducerService.class);

    @Inject
    @JMSConnectionFactory(value = "java:comp/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    @Override
    public void produceMessage(String message) {
        LOG.debug("produceMessage has started");
        try {
            jmsContext.createProducer().send(queue, message);
        } catch (JMSRuntimeException ex) {
            LOG.error("produceMessage has failed: ", ex);
            throw ex;
        }
        LOG.debug("produceMessage has ended");
    }

}
