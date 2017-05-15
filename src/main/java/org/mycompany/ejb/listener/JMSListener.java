package org.mycompany.ejb.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by konstantinamp on 5/10/17.
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/ExpiryQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class JMSListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(JMSListener.class);

    @Override
    public void onMessage(Message message) {
        LOG.debug("handle message has started");
        LOG.debug("message={}", message);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException ex) {
            LOG.error("error occurs during thread sleep in JMSListener.onMessage", ex);
        }
        /*
         * handle message
         */
        LOG.debug("handle message has ended");
    }
}
