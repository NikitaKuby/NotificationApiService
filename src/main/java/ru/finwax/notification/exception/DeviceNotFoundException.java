package ru.finwax.notification.exception;


public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}