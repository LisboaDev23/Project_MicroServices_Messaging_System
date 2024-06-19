package com.microServiceMessage.user.producers;

import com.microServiceMessage.user.dtos.EmailDto;
import com.microServiceMessage.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    //1. need a instance of the RabbitTemplate
    final RabbitTemplate rabbitTemplate;

    //create constructor where need the RabbitTemplate instance
    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //need return name of the queue, she is a routingKey -> value name on the props
    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem vindo! \nAgradecemos o seu cadastro e sua confiança, aproveite todos os recursos da plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
