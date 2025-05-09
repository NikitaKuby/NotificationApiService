package ru.finwax.notification.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.finwax.notification.model.Device;
import ru.finwax.notification.model.dto.DeviceRegistrationRequest;
import ru.finwax.notification.service.DeviceService;

@RestController
@RequestMapping("/url/api/v0/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    @PostMapping
    public ResponseEntity<Device> registerDevice(@Valid @RequestBody DeviceRegistrationRequest deviceId) {
        Device device = deviceService.registerDevice(deviceId.deviceId());
        return ResponseEntity.ok(device);
    }
}