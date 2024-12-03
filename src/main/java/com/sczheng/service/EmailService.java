package com.sczheng.service;

public interface EmailService {

    public void sendDeliveryNotification(String to, String orderInfo);
}
