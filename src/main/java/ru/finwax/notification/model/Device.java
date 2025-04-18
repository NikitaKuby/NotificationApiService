package ru.finwax.notification.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", unique = true, nullable = false)
    private String deviceId;

    @Column(name = "notification_count", nullable = false)
    private int notificationCount = 0;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Notification> notifications = new ArrayList<>();

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.setDevice(this);
        notificationCount++;
    }
}