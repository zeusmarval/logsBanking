package com.zama.logsBanking.drivenAdapters.repositorios;

import com.zama.logsBanking.models.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_Repositorio_Message extends ReactiveMongoRepository<Message, String> {
}
