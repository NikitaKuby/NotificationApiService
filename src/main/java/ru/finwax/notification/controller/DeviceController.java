package ru.finwax.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.finwax.notification.model.Device;
import ru.finwax.notification.service.DeviceService;

@RestController
@RequestMapping("/url/api/v0/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<Device> registerDevice(@RequestParam String deviceId) {
        Device device = deviceService.registerDevice(deviceId);
        return ResponseEntity.ok(device);
    }
}