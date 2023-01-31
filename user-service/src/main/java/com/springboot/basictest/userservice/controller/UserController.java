package com.springboot.basictest.userservice.controller;

import com.springboot.basictest.userservice.dto.*;
import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.service.UserService;
import com.springboot.basictest.userservice.service.UserSettingService;
import com.springboot.basictest.userservice.util.UserSettingEnum;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserSettingService userSettingService;

    @GetMapping("/v1/users")
    public ResponseEntity<ListUserResponseDto> retrieveAllUser(
            @RequestParam(value = "max_records", required = false) Integer maxRecords,
            @RequestParam(required = false) Integer offset){
        maxRecords = maxRecords == null ? 5 : maxRecords;
        offset = offset == null ? 0 : offset;
        return userService.findAll(maxRecords, offset);
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> retrieveUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/v1/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.save(userRequestDto);
    }

    @PutMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable int id, @Valid @RequestBody UserRequestDto userRequestDto){
        return userService.update(id, userRequestDto);
    }

    @PutMapping("/v1/users/{id}/settings")
    public ResponseEntity<UserResponseDto> updateUserSetting(@PathVariable int id,
                                                             @RequestBody List<HashMap<String, String>> userSettingRequestDtoList){

        BiometricLoginDto biometricLoginDto = new BiometricLoginDto();
        PushNotificationDto pushNotificationDto = new PushNotificationDto();
        ShowOnboardingDto showOnboardingDto = new ShowOnboardingDto();
        SmsNotificationDto smsNotificationDto = new SmsNotificationDto();
        WidgetOrderDto widgetOrderDto = new WidgetOrderDto();

        for(HashMap<String, String> map : userSettingRequestDtoList){
            for(Map.Entry<String, String> entry: map.entrySet()){
                if (entry.getKey().equals(UserSettingEnum.BIOMETRIC_LOGIN.getKey())){
                    biometricLoginDto.setBiometricLogin(entry.getValue());
                }
                if (entry.getKey().equals(UserSettingEnum.PUSH_NOTIFICATION.getKey())){
                    pushNotificationDto.setPushNotification(entry.getValue());
                }
                if (entry.getKey().equals(UserSettingEnum.SHOW_ONBOARDING.getKey())){
                    showOnboardingDto.setShowOnboarding(entry.getValue());
                }
                if (entry.getKey().equals(UserSettingEnum.SMS_NOTIFICATION.getKey())){
                    smsNotificationDto.setSmsNotification(entry.getValue());
                }
                if (entry.getKey().equals(UserSettingEnum.WIDGET_ORDER.getKey())){
                    widgetOrderDto.setWidgetOrder(entry.getValue());
                }
            }
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        validator.validate(biometricLoginDto);
        validator.validate(pushNotificationDto);
        validator.validate(showOnboardingDto);
        validator.validate(smsNotificationDto);
        validator.validate(widgetOrderDto);
        return userSettingService.update(id, biometricLoginDto, pushNotificationDto, showOnboardingDto, smsNotificationDto,
                widgetOrderDto);
    }

    @DeleteMapping("/v1/users/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        return userService.deleteById(id);
    }

    @PutMapping("/v1/users/{id}/refresh")
    public ResponseEntity<UserResponseDto> refresh(@PathVariable int id){
        return userService.refresh(id);
    }
}
