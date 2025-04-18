-- Создание таблицы устройств
CREATE TABLE devices (
                         id BIGSERIAL PRIMARY KEY,
                         device_id VARCHAR(255) UNIQUE NOT NULL,
                         notification_count INTEGER NOT NULL DEFAULT 0
);

-- Создание таблицы уведомлений
CREATE TABLE notifications (
                               id BIGSERIAL PRIMARY KEY,
                               device_id BIGINT NOT NULL,
                               msg TEXT NOT NULL,
                               local_time VARCHAR(255),
                               time TIMESTAMP NOT NULL,
                               sender VARCHAR(255) NOT NULL,
                               sim_detail VARCHAR(255),
                               rule_name VARCHAR(255),
                               app_name VARCHAR(255),
                               formatted_msg TEXT,
                               is_processed BOOLEAN NOT NULL DEFAULT FALSE,
                               CONSTRAINT fk_notification_device FOREIGN KEY (device_id) REFERENCES devices(id)
);

-- Создание таблицы обработанных уведомлений
CREATE TABLE processed_notifications (
                                         id BIGSERIAL PRIMARY KEY,
                                         device_id BIGINT NOT NULL,
                                         sender VARCHAR(255) NOT NULL,
                                         message TEXT NOT NULL,
                                         new_time TIMESTAMP NOT NULL,
                                         CONSTRAINT fk_processed_notification_device FOREIGN KEY (device_id) REFERENCES devices(id)
);

-- Создание индексов для улучшения производительности
CREATE INDEX idx_notifications_device_id ON notifications(device_id);
CREATE INDEX idx_notifications_is_processed ON notifications(is_processed);
CREATE INDEX idx_processed_notifications_device_id ON processed_notifications(device_id);