package com.microServiceMessage.user.services;

import com.microServiceMessage.user.models.UserModel;
import com.microServiceMessage.user.producers.UserProducer;
import com.microServiceMessage.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;

    //after create user producer with all configuration dependencies, need dependency producer...
    final UserProducer userProducer;
    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }
    @Transactional //utilizado para caso dê erro na transação, realizamos rollback na última operação feita para tudo ser  voltado ao normal
    public UserModel save(UserModel userModel){
        userModel = userRepository.save(userModel); //save on database
        userProducer.publishMessageEmail(userModel);//after user save on database, we too send message email to user saying success!
        return userModel;
    }
}
