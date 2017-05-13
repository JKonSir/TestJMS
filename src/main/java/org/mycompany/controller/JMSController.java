package org.mycompany.controller;

import org.mycompany.service.JMSService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by konstantinamp on 5/10/17.
 */
@RestController
@RequestMapping(value = "/test_jms_controller")
public class JMSController {

    private final JMSService testService;

    @Inject
    public JMSController(JMSService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendMessage(@RequestParam(name = "message") String message) {
        testService.testMethodService(message);
    }

}
