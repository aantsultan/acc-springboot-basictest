package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.*;
import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.entity.UserSettingEntity;
import com.springboot.basictest.userservice.exception.UserNotFoundException;
import com.springboot.basictest.userservice.repository.UserRepository;
import com.springboot.basictest.userservice.repository.UserSettingRepository;
import com.springboot.basictest.userservice.util.UserSettingEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSettingServiceImpl implements UserSettingService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSettingRepository userSettingRepository;

    private final DateTimeFormatter formatDate =
            DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));

    @Override
    public ResponseEntity<UserResponseDto> update(int id,
                                                  BiometricLoginDto biometricLoginDto,
                                                  PushNotificationDto pushNotificationDto,
                                                  ShowOnboardingDto showOnboardingDto,
                                                  SmsNotificationDto smsNotificationDto,
                                                  WidgetOrderDto widgetOrderDto) {

        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isEmpty()){
            throw new UserNotFoundException("Cannot find resource with id "+id);
        }

        UserEntity userEntityResponse = userEntity.get();
        List<UserSettingEntity> userSettingEntityList = userEntityResponse.getUserSettings();

        for(UserSettingEntity userSettingEntity: userSettingEntityList){
            if(userSettingEntity.getKeyName().equals(UserSettingEnum.BIOMETRIC_LOGIN.getKey())){
                userSettingEntity.setValueName(biometricLoginDto.getBiometricLogin());
            }
            if(userSettingEntity.getKeyName().equals(UserSettingEnum.PUSH_NOTIFICATION.getKey())){
                userSettingEntity.setValueName(pushNotificationDto.getPushNotification());
            }
            if(userSettingEntity.getKeyName().equals(UserSettingEnum.SHOW_ONBOARDING.getKey())){
                userSettingEntity.setValueName(showOnboardingDto.getShowOnboarding());
            }
            if(userSettingEntity.getKeyName().equals(UserSettingEnum.SMS_NOTIFICATION.getKey())){
                userSettingEntity.setValueName(smsNotificationDto.getSmsNotification());
            }
            if(userSettingEntity.getKeyName().equals(UserSettingEnum.WIDGET_ORDER.getKey())){
                userSettingEntity.setValueName(widgetOrderDto.getWidgetOrder());
            }
            userSettingRepository.save(userSettingEntity);
        }


        UserResponseDto responseDto = new UserResponseDto();
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setActive(userEntityResponse.getActive());
        userDataDto.setId(userEntityResponse.getId());
        userDataDto.setBirthDate(userEntityResponse.getBirthDate());
        userDataDto.setSsn(userEntityResponse.getSsn());
        userDataDto.setCreatedBy(userEntityResponse.getCreatedBy());
        userDataDto.setCreatedTime(userEntityResponse.getCreatedTime().format(formatDate));
        userDataDto.setFirstName(userEntityResponse.getFirstName());
        userDataDto.setLastName(userEntityResponse.getFamilyName());
        userDataDto.setUpdatedBy(userEntityResponse.getUpdatedBy());
        userDataDto.setUpdatedTime(userEntityResponse.getUpdatedTime().format(formatDate));

        List<Object> userSettingResponseList = new ArrayList<>();
        userSettingResponseList.add(biometricLoginDto);
        userSettingResponseList.add(pushNotificationDto);
        userSettingResponseList.add(showOnboardingDto);
        userSettingResponseList.add(smsNotificationDto);
        userSettingResponseList.add(widgetOrderDto);

        responseDto.setUserData(userDataDto);
        responseDto.setUserSetting(userSettingResponseList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
