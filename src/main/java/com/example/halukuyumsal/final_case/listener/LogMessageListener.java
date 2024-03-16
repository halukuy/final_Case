package com.example.halukuyumsal.final_case.listener;

import com.example.halukuyumsal.final_case.model.LogMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LogMessageListener {

    @RabbitListener(queues = "logQueue")
    public void handleMessage(LogMessage message) {
        System.out.println("Received log message: " + message.getMessage());
    }
}