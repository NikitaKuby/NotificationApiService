package ru.finwax.notification.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notification_device"))
    private Device device;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String msg;

    @Column(name = "local_time")
    private String localTime;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime time;

    @Column(nullable = false)
    @NotBlank
    private String sender;

    @Column(name = "sim_detail")
    private String simDetail;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "formatted_msg")
    private String formattedMsg;

    @Column(name = "is_processed", nullable = false)
    private boolean isProcessed = false;
}