package com.example.mqttlistenerdemo;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttSubscriber implements MqttCallback {

    private final MqttClient mqttClient;

    @Value("${mqtt.topic}")
    private String topic;

    public MqttSubscriber(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
        this.mqttClient.setCallback(this);
    }

    @PostConstruct
    public void subscribeToTopic() {
        try {
            mqttClient.subscribe(topic);
            System.out.println("Subscribed to topic: " + topic);
        } catch (MqttException e) {
            System.err.println("Subscription failed: " + e.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.err.println("Connection lost. Attempting reconnection...");
        try {
            mqttClient.reconnect();
        } catch (MqttException e) {
            System.err.println("Reconnection failed: " + e.getMessage());
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("Message arrived:");
        System.out.println("  Topic: " + topic);
        System.out.println("  Payload: " + new String(message.getPayload()));
        // You can process the message here.
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // This method can remain empty since it's only relevant for message publishers.
    }
}