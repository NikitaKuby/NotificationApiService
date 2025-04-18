package ru.finwax.notification.repository;

import ru.finwax.notification.model.ProcessedNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedNotificationRepository extends JpaRepository<ProcessedNotification, Long> {
}

