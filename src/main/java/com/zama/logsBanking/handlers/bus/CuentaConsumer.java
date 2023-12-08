package com.zama.logsBanking.handlers.bus;

import com.google.gson.Gson;
import com.zama.logsBanking.RabbitConfig;
import com.zama.logsBanking.drivenAdapters.repositorios.I_RepositorioCuentaMongo;
import com.zama.logsBanking.drivenAdapters.repositorios.I_Repositorio_Message;
import com.zama.logsBanking.models.DTO.M_Cuenta_DTO;
import com.zama.logsBanking.models.Mongo.M_CuentaMongo;
import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import com.zama.logsBanking.services.I_Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

import java.time.LocalDateTime;

@Component
@Order(1)
public class CuentaConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;
    @Autowired
    I_Repositorio_Message iRepositorioMessage;
    @Autowired
    private I_RepositorioCuentaMongo iRepositorioCuentaMongo;

    @Autowired
    I_Message iMessage;
    @Autowired
    private Gson gson;
    @Override
    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ACCOUNTS)
                .map(message -> {
                    M_Cuenta_DTO cuenta = gson
                            .fromJson(new String(message.getBody()), M_Cuenta_DTO.class);

                    M_MessageMongo messageMongo = new M_MessageMongo();

                    messageMongo.setDateT(LocalDateTime.now());
                    messageMongo.setOrigin(RabbitConfig.QUEUE_NAME_ACCOUNTS);
                    messageMongo.setMessageType("Cuenta: ");
                    messageMongo.setMessage(cuenta.toString());

                    iMessage.saveMessage(messageMongo).subscribe();

                    return cuenta;
                }).subscribe();


        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ERRORS_ACCOUNTS)
                .map(message -> {
                    M_CuentaMongo cuenta = gson
                            .fromJson(new String(message.getBody()), M_CuentaMongo.class);

                    M_MessageMongo messageMongo = new M_MessageMongo();

                    messageMongo.setDateT(LocalDateTime.now());
                    messageMongo.setOrigin(RabbitConfig.QUEUE_NAME_ERRORS_ACCOUNTS);
                    messageMongo.setMessageType("Error Cuenta: ");
                    messageMongo.setMessage(cuenta.toString());

                    String idCuenta = cuenta.getId();

                    iMessage.saveMessage(messageMongo).subscribe();
                    iRepositorioCuentaMongo.deleteById(idCuenta).subscribe();

                    return message;

                }).subscribe();

    }

}
