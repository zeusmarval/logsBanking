package com.zama.logsBanking.services;

import com.zama.logsBanking.drivenAdapters.repositorios.I_Repositorio_Message;
import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service()
public class Message_Imp  implements I_Message{

    @Autowired
    I_Repositorio_Message iRepositorioMessage;
    @Override
    public Mono<M_MessageMongo> saveMessage(M_MessageMongo messageMongo) {
        System.out.println(
                messageMongo.getDateT() + ": " +
                        messageMongo.getMessageType() +
                        messageMongo.getMessage());
        return iRepositorioMessage.save(messageMongo);

    }
}
