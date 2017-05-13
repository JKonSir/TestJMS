package org.mycompany.ejb.consumer.impl;

import org.mycompany.ejb.listener.JMSListener;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

/**
 * Created by konstantinamp on 5/10/17.
 */
@Stateless(mappedName = "ejb/RegisterConsumerServiceImpl")
public class RegisterConsumerServiceImpl implements org.mycompany.ejb.consumer.RegisterConsumerService {

    @Inject
    @JMSConnectionFactory(value = "java:comp/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    @Override
    public void registerConsumer() {
        try {
            JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
            jmsConsumer.setMessageListener(new JMSListener());
        } catch (JMSRuntimeException ex) {

        }
    }

}
