package ru.finwax.notification.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.finwax.notification.exception.DeviceNotFoundException;
import ru.finwax.notification.model.Device;
import ru.finwax.notification.model.Notification;
import ru.finwax.notification.repository.DeviceRepository;
import ru.finwax.notification.repository.NotificationRepository;


@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public Notification saveNotification(String deviceId, @Valid Notification notification) {
        Device device = deviceRepository.findByDeviceId(deviceId)
            .orElseThrow(() -> {
                log.error("Attempt to save notification for non-existent device: {}", deviceId);
                return new DeviceNotFoundException("Device not found: " + deviceId);
            });

        notification.setDevice(device);

        notificationRepository.save(notification);
        deviceRepository.save(device);

        log.info("Saved notification for device {}: {}", deviceId, notification);
        return notification;
    }
}