package com.zama.logsBanking.drivenAdapters.repositorios;

import com.zama.logsBanking.models.Mongo.M_MessageMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_Repositorio_Message extends ReactiveMongoRepository<M_MessageMongo, String> {

}
