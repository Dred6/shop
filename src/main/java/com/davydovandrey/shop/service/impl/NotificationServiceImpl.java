package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.Notification;
import com.davydovandrey.shop.repository.NotificationRepository;
import com.davydovandrey.shop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getListNotification() {
        return notificationRepository.findAllByOrderByDateDesc();
    }
}
