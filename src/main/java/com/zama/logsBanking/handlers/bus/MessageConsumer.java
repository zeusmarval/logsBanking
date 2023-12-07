package com.zama.logsBanking.handlers.bus;

import com.google.gson.Gson;
import com.zama.logsBanking.RabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

@Component
public class MessageConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Gson gson;

    @Override
    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME)
                .map(message -> {
                    return message;
                }).subscribe();
    }

}
