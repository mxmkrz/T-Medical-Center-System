package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitSender {

    private final RabbitConfig rabbitConfig;
    @Autowired
    public RabbitSender(RabbitConfig rabbitConfig) {
        this.rabbitConfig = rabbitConfig;
    }

    /**
     * This is method necessary for send message for second app
     *
     * @param message the message
     */

    public void sendMessage(String message){
        rabbitConfig.produce(message);
        log.info("send " + message);
    }

}
