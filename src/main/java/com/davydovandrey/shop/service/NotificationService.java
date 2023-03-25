package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.Notification;

import java.util.List;

public interface NotificationService {
    void addNotification(Notification notification);
    List<Notification> getListNotification();
}
