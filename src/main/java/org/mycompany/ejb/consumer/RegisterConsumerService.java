package org.mycompany.ejb.consumer;

import javax.ejb.Local;

/**
 * Created by konstantinamp on 5/12/17.
 */
@Local
public interface RegisterConsumerService {
    void registerConsumer();
}
