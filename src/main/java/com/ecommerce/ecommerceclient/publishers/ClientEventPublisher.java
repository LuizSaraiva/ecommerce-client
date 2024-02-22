package com.ecommerce.ecommerceclient.publishers;

import com.ecommerce.ecommerceclient.api.mapper.dto.ClientEventDto;
import com.ecommerce.ecommerceclient.enums.ActionType;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ClientEventPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${rabbitmq.broker.exchange}")
    private String exchangeClientEvent;

    @Value(value = "${rabbitmq.broker.routing")
    private String routingClientEvent;

    public void publisherClientEvent(ClientEventDto clientEventDto, ActionType actionType){
        clientEventDto.setActionType(actionType.toString());
        rabbitTemplate.convertAndSend(exchangeClientEvent, routingClientEvent, clientEventDto);
        log.debug("Message send to broker.");
    }
}
