package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOrderByDateDesc();
}
