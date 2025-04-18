package ru.finwax.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finwax.notification.model.Device;
import ru.finwax.notification.repository.DeviceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Transactional
    public Device registerDevice(String deviceId) {
        if (deviceRepository.existsByDeviceId(deviceId)) {
            log.warn("Device with id {} already exists", deviceId);
            throw new IllegalArgumentException("Device already exists");
        }

        Device device = new Device();
        device.setDeviceId(deviceId);

        Device savedDevice = deviceRepository.save(device);
        log.info("Registered new device with id: {}", deviceId);

        return savedDevice;
    }

    public Device getDeviceByDeviceId(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId)
            .orElseThrow(() -> {
                log.error("Device with id {} not found", deviceId);
                return new IllegalArgumentException("Device not found");
            });
    }
}