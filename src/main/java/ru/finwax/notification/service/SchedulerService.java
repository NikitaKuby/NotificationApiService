package ru.finwax.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finwax.notification.model.Notification;
import ru.finwax.notification.model.ProcessedNotification;
import ru.finwax.notification.repository.NotificationRepository;
import ru.finwax.notification.repository.ProcessedNotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final NotificationRepository notificationRepository;
    private final ProcessedNotificationRepository processedNotificationRepository;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void processUnprocessedNotifications() {
        List<Notification> unprocessedNotifications = notificationRepository.findByIsProcessedFalse();

        if (unprocessedNotifications.isEmpty()) {
            log.debug("No unprocessed notifications found");
            return;
        }

        log.info("Processing {} unprocessed notifications", unprocessedNotifications.size());

        for (Notification notification : unprocessedNotifications) {
            try {
                ProcessedNotification processedNotification = new ProcessedNotification();
                processedNotification.setDevice(notification.getDevice());
                processedNotification.setSender(notification.getSender());
                processedNotification.setMessage(notification.getMsg());
                processedNotification.setNewTime(LocalDateTime.now());

                processedNotificationRepository.save(processedNotification);
                notification.setProcessed(true);
                notificationRepository.save(notification);

                log.debug("Processed notification ID: {}", notification.getId());
            } catch (Exception e) {
                log.error("Error processing notification ID: {}", notification.getId(), e);
            }
        }

        log.info("Finished processing notifications");
    }
}