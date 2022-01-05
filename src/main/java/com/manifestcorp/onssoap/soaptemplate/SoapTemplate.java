package com.manifestcorp.onssoap.soaptemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import java.time.LocalDateTime;
import static java.lang.Thread.sleep;

@SpringBootApplication(scanBasePackages = "com.manifestcorp.onssoap.soaptemplate")
@EnableJms
public class SoapTemplate {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SoapTemplate.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        for(int i = 0; i < 10; i++) {
            String message = "Date: " + LocalDateTime.now();
            jmsTemplate.convertAndSend("DEV.QUEUE.1", message);
            System.out.println("Message sent: " + message);
        }
        System.out.println("Done...");
    }
}
//@Endpoint
//public class SoapTemplate {
//
//    @Autowired
//    JmsTemplate jmsTemplate;
//
//    public void soapQueue() {
//        for (int i = 0; i < 10; i++) {
//            String message = "Date: " + LocalDateTime.now();
//            jmsTemplate.convertAndSend("DEV.QUEUE.1", message);
//            System.out.println("Message sent: " + message);
//        }
//    }
//}
//

