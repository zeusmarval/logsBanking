package com.zama.logsBanking.services;

import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import reactor.core.publisher.Mono;

public interface I_Message {
    Mono<M_MessageMongo> saveMessage(M_MessageMongo messageMongo);

}
