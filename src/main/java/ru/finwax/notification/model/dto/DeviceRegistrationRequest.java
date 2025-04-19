package ru.finwax.notification.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record DeviceRegistrationRequest(
    @NotBlank @Size(min=1, max=50)
    @JsonProperty("deviceId")
    String deviceId
) {
}