package org.mycompany.ejb.consumer.impl;

import org.mycompany.ejb.consumer.RegisterConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

/**
 * Created by konstantinamp on 5/10/17.
 */
@Stateless(mappedName = "ejb/RegisterConsumerServiceImpl")
public class RegisterConsumerServiceImpl implements RegisterConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterConsumerService.class);

    @Inject
    @JMSConnectionFactory(value = "java:comp/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    @Override
    public void registerConsumer() {
        LOG.debug("registerConsumer has started");
        try {
            jmsContext.createConsumer(queue);
        } catch (JMSRuntimeException ex) {
            LOG.error("registerConsumer has failed: ", ex);
        }
        LOG.debug("registerConsumer has ended");
    }

}
