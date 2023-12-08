package com.zama.logsBanking.drivenAdapters.repositorios;

import com.zama.logsBanking.models.Mongo.M_CuentaMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_RepositorioCuentaMongo extends ReactiveMongoRepository<M_CuentaMongo, String>
{
}
