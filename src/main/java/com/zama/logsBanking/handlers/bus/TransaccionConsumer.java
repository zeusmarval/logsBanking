package com.zama.logsBanking.handlers.bus;

import com.google.gson.Gson;
import com.zama.logsBanking.RabbitConfig;
import com.zama.logsBanking.drivenAdapters.repositorios.I_RepositorioCuentaMongo;
import com.zama.logsBanking.drivenAdapters.repositorios.I_Repositorio_Message;
import com.zama.logsBanking.drivenAdapters.repositorios.I_Repositorio_TransaccionMongo;
import com.zama.logsBanking.models.DTO.M_Transaccion_DTO;
import com.zama.logsBanking.models.Mongo.M_CuentaMongo;
import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import com.zama.logsBanking.models.Mongo.M_TransaccionMongo;
import com.zama.logsBanking.services.I_Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

import java.time.LocalDateTime;

@Component
@Order(2)
public class TransaccionConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;
    @Autowired
    I_Repositorio_Message iRepositorioMessage;
    @Autowired
    private I_RepositorioCuentaMongo iRepositorioCuentaMongo;
    @Autowired
    private I_Repositorio_TransaccionMongo iRepositorioTransaccionMongo;
    @Autowired
    I_Message iMessage;
    @Autowired
    private Gson gson;

    @Override
    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_TRANSACTIONS)
                .map(message -> {
                    M_Transaccion_DTO transaction = gson
                            .fromJson(new String(message.getBody()), M_Transaccion_DTO.class);

                    M_MessageMongo messageMongo = new M_MessageMongo();

                    messageMongo.setDateT(LocalDateTime.now());
                    messageMongo.setOrigin(RabbitConfig.QUEUE_NAME_TRANSACTIONS);
                    messageMongo.setMessageType("Transaccion Creada: ");
                    messageMongo.setMessage(transaction.toString());

                    iMessage.saveMessage(messageMongo).subscribe();

                    return message;
                }).subscribe();

        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ERRORS_TRANSACTIONS)
                .map(message -> {
                    M_TransaccionMongo transaction = gson
                            .fromJson(new String(message.getBody()), M_TransaccionMongo.class);

                    M_MessageMongo messageMongo = new M_MessageMongo();

                    messageMongo.setDateT(LocalDateTime.now());
                    messageMongo.setOrigin(RabbitConfig.QUEUE_NAME_ERRORS_TRANSACTIONS);
                    messageMongo.setMessageType("Transaccion Fallida: ");
                    messageMongo.setMessage(transaction.toString());

                    iMessage.saveMessage(messageMongo).subscribe();

                    M_CuentaMongo cuenta = transaction.getCuenta();
                    cuenta.setSaldo_Global(transaction.getSaldo_inicial());
                    iRepositorioCuentaMongo.save(cuenta).subscribe();
                    iRepositorioTransaccionMongo.deleteById(transaction.getId()).subscribe();

                    return message;

                }).subscribe();

    }

}
