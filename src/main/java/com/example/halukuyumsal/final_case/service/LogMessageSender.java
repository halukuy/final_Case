package com.example.halukuyumsal.final_case.service;

import com.example.halukuyumsal.final_case.model.LogMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogMessageSender {
    private static final String EXCHANGE_NAME = "logsExchange";
    private static final String ROUTING_KEY = "log";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendLogMessage(LogMessage logMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, logMessage);
    }
}