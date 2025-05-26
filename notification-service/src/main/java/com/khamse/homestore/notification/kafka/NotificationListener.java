package com.khamse.homestore.notification.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Received notification message: {}", record.value());
        // Here you would implement actual notification logic (email, SMS, etc.)
    }
}
