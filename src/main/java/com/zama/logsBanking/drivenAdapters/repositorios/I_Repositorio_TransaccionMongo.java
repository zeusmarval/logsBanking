package com.zama.logsBanking.drivenAdapters.repositorios;

import com.zama.logsBanking.models.Mongo.M_TransaccionMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_Repositorio_TransaccionMongo extends ReactiveMongoRepository<M_TransaccionMongo, String>
{
}
