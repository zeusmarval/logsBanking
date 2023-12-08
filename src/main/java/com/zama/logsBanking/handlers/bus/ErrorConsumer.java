package com.zama.logsBanking.handlers.bus;

import com.google.gson.Gson;
import com.zama.logsBanking.RabbitConfig;
import com.zama.logsBanking.drivenAdapters.repositorios.I_Repositorio_Message;
import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import com.zama.logsBanking.services.I_Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

import java.time.LocalDateTime;

@Component
@Order(4)
public class ErrorConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;
    @Autowired
    I_Repositorio_Message iRepositorioMessage;
    @Autowired
    I_Message iMessage;
    @Autowired
    private Gson gson;
    @Override
    public void run(String... args) throws Exception {

        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ERRORS)
                .map(message -> {
                    String errorMessage = new String(message.getBody());

                    M_MessageMongo messageMongo = new M_MessageMongo();

                    messageMongo.setDateT(LocalDateTime.now());
                    messageMongo.setOrigin(RabbitConfig.QUEUE_NAME_ERRORS);
                    messageMongo.setMessageType("Error: ");
                    messageMongo.setMessage(errorMessage);

                    iMessage.saveMessage(messageMongo).subscribe();

                    return errorMessage;

                }).subscribe();

    }

}
