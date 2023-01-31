package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSettingService {
    ResponseEntity<UserResponseDto> update(int id,
                                           BiometricLoginDto biometricLoginDto,
                                           PushNotificationDto pushNotificationDto,
                                           ShowOnboardingDto showOnboardingDto,
                                           SmsNotificationDto smsNotificationDto,
                                           WidgetOrderDto widgetOrderDto);
}
