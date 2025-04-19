package ru.finwax.notification.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.finwax.notification.exception.DeviceNotFoundException;
import ru.finwax.notification.model.Notification;
import ru.finwax.notification.service.NotificationService;

@Slf4j
@RestController
@RequestMapping("/url/api/v0/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/device/{deviceId}")
    public ResponseEntity<Notification> receiveNotification(
        @PathVariable String deviceId,
        @RequestBody @Valid Notification notification) {
        try {
            return ResponseEntity.ok( notificationService.saveNotification(deviceId, notification));
        } catch (DeviceNotFoundException e) {
            log.error("Device not found error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
