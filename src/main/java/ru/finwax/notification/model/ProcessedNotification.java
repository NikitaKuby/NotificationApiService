package ru.finwax.notification.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "processed_notifications")
public class ProcessedNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false, foreignKey = @ForeignKey(name = "fk_processed_notification_device"))
    private Device device;

    @Column(nullable = false)
    @NotBlank
    private String sender;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String message;

    @Column(name = "new_time", nullable = false)
    @NotNull
    private LocalDateTime newTime;
}