package ru.finwax.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finwax.notification.exception.DeviceNotFoundException;
import ru.finwax.notification.model.Device;
import ru.finwax.notification.repository.DeviceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Transactional
    public Device registerDevice(String deviceId) {
        deviceRepository.findByDeviceId(deviceId)
            .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        Device device = new Device();
        device.setDeviceId(deviceId);

        Device savedDevice = deviceRepository.save(device);
        log.info("Registered new device with id: {}", deviceId);

        return savedDevice;
    }

}