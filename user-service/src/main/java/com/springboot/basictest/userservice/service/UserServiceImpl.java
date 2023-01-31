package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.*;
import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.entity.UserSettingEntity;
import com.springboot.basictest.userservice.exception.UserAlreadyExistException;
import com.springboot.basictest.userservice.exception.UserNotFoundException;
import com.springboot.basictest.userservice.repository.UserRepository;
import com.springboot.basictest.userservice.repository.UserSettingRepository;
import com.springboot.basictest.userservice.util.ExceptionEnum;
import com.springboot.basictest.userservice.util.UserSettingEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    private static final String defaultName = "SYSTEM";
    private final DateTimeFormatter formatDate =
            DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));

    @Override
    public ResponseEntity<ListUserResponseDto> findAll(Integer maxRecords, Integer offset) {
        Pageable pageable = PageRequest.of(offset, maxRecords);
        List<UserEntity> userEntityList
                = userRepository.findAll(pageable).getContent();
        List<UserDataDto> userDataDtoList = new ArrayList<>();
        for(UserEntity entity : userEntityList){
            UserDataDto userDataDto =
                    new UserDataDto(
                            entity.getId(),
                            entity.getSsn(),
                            entity.getFirstName(),
                            entity.getFamilyName(),
                            entity.getBirthDate(),
                            entity.getCreatedTime().format(formatDate),
                            entity.getUpdatedTime().format(formatDate),
                            entity.getCreatedBy(),
                            entity.getUpdatedBy(),
                            entity.getActive()
                    );
            userDataDtoList.add(userDataDto);
        }
            ListUserResponseDto listUserResponseDto
                    = new ListUserResponseDto(userDataDtoList, maxRecords, offset);
        return new ResponseEntity<>(listUserResponseDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> findById(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.get() == null){
            throw new UserNotFoundException("Cannot find resource with id " + id);
        }
        UserEntity userEntityResponse = userEntityOptional.get();
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
        List<UserSettingEntity> userSettingEntityList = userEntityResponse.getUserSettings();
        for(UserSettingEntity userSettingEntity: userSettingEntityList){
            String keyName = userSettingEntity.getKeyName();
            if(keyName.equals(UserSettingEnum.BIOMETRIC_LOGIN.getKey())){
                userSettingResponseList.add(new BiometricLoginDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.PUSH_NOTIFICATION.getKey())){
                userSettingResponseList.add(new PushNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SHOW_ONBOARDING.getKey())){
                userSettingResponseList.add( new ShowOnboardingDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SMS_NOTIFICATION.getKey())){
                userSettingResponseList.add(new SmsNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.WIDGET_ORDER.getKey())){
                userSettingResponseList.add(new WidgetOrderDto(userSettingEntity.getValueName()));
            }
        }

        responseDto.setUserData(userDataDto);
        responseDto.setUserSetting(userSettingResponseList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> save(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        String ssn = userRequestDto.getSsn();

        userEntity.setSsn(ssn);
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setFamilyName(userRequestDto.getLastName());
        userEntity.setBirthDate(userRequestDto.getBirthDate());

        UserEntity optionalUserEntity = userRepository.findUserBySsn(ssn);
        if(optionalUserEntity != null){
            throw new UserAlreadyExistException(ExceptionEnum.ALREADY_EXIST.getMessage());
        }

        UserSettingLovDto userSettingLovDto = new UserSettingLovDto().getInstance();
        UserSettingEntity bioMetricSettingEntity = new UserSettingEntity(
                UserSettingEnum.BIOMETRIC_LOGIN.getKey(),
                userSettingLovDto.getBiometricLogin(),
                userEntity);
        UserSettingEntity pushNotifSettingEntity = new UserSettingEntity(
                UserSettingEnum.PUSH_NOTIFICATION.getKey(),
                userSettingLovDto.getPushNotification(),
                userEntity);
        UserSettingEntity showOnboardSettingEntity = new UserSettingEntity(
                UserSettingEnum.SHOW_ONBOARDING.getKey(),
                userSettingLovDto.getShowOnboarding(),
                userEntity);
        UserSettingEntity smsNotifSettingEntity = new UserSettingEntity(
                UserSettingEnum.SMS_NOTIFICATION.getKey(),
                userSettingLovDto.getSmsNotification(),
                userEntity);
        UserSettingEntity widgetOrderSettingEntity = new UserSettingEntity(
                UserSettingEnum.WIDGET_ORDER.getKey(),
                userSettingLovDto.getWidgetOrder(),
                userEntity);
        List<UserSettingEntity> userSettingEntityList = new ArrayList<>();
        userSettingEntityList.add(bioMetricSettingEntity);
        userSettingEntityList.add(pushNotifSettingEntity);
        userSettingEntityList.add(showOnboardSettingEntity);
        userSettingEntityList.add(smsNotifSettingEntity);
        userSettingEntityList.add(widgetOrderSettingEntity);

        userEntity.setActive(true);
        userEntity.setCreatedTime(LocalDateTime.now());
        userEntity.setUpdatedTime(LocalDateTime.now());
        userEntity.setCreatedBy(defaultName);
        userEntity.setUpdatedBy(defaultName);

        UserEntity userEntityResponse = userRepository.save(userEntity);
        for(UserSettingEntity userSettingEntity : userSettingEntityList){
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
        userSettingResponseList.add(new BiometricLoginDto(bioMetricSettingEntity.getValueName()));
        userSettingResponseList.add(new PushNotificationDto(pushNotifSettingEntity.getValueName()));
        userSettingResponseList.add( new ShowOnboardingDto(showOnboardSettingEntity.getValueName()));
        userSettingResponseList.add(new SmsNotificationDto(smsNotifSettingEntity.getValueName()));
        userSettingResponseList.add(new WidgetOrderDto(widgetOrderSettingEntity.getValueName()));

        responseDto.setUserData(userDataDto);
        responseDto.setUserSetting(userSettingResponseList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> update(int id, UserRequestDto userRequestDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.get();

        if(userEntityOptional.isEmpty()){
            throw new UserNotFoundException("Cannot find resource with id "+id);
        }

        String ssn = userRequestDto.getSsn();
        UserEntity userEntityBySsn = userRepository.findUserBySsn(ssn);
        if(userEntityBySsn != null && !(userEntity.getSsn().equals(ssn))){
            throw new UserAlreadyExistException("Record with unique value "+ssn+" already exists in the system");
        }

        userEntity.setSsn(ssn);
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setFamilyName(userRequestDto.getLastName());
        userEntity.setBirthDate(userRequestDto.getBirthDate());
        userEntity.setUpdatedTime(LocalDateTime.now());

        UserEntity userEntityResponse = userRepository.save(userEntity);

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
        List<UserSettingEntity> userSettingEntityList = userEntityResponse.getUserSettings();
        for(UserSettingEntity userSettingEntity: userSettingEntityList){
            String keyName = userSettingEntity.getKeyName();
            if(keyName.equals(UserSettingEnum.BIOMETRIC_LOGIN.getKey())){
                userSettingResponseList.add(new BiometricLoginDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.PUSH_NOTIFICATION.getKey())){
                userSettingResponseList.add(new PushNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SHOW_ONBOARDING.getKey())){
                userSettingResponseList.add( new ShowOnboardingDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SMS_NOTIFICATION.getKey())){
                userSettingResponseList.add(new SmsNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.WIDGET_ORDER.getKey())){
                userSettingResponseList.add(new WidgetOrderDto(userSettingEntity.getValueName()));
            }
        }

        responseDto.setUserData(userDataDto);
        responseDto.setUserSetting(userSettingResponseList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserResponseDto> deleteById(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){
            throw new UserNotFoundException("Cannot find resource with id "+id);
        }

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setActive(false);
        userEntity.setDeletedTime(LocalDateTime.now());

        userRepository.save(userEntity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UserResponseDto> refresh(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){
            throw new UserNotFoundException("Cannot find resource with id "+id);
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setDeletedTime(null);
        userEntity.setActive(true);
        UserEntity userEntityResponse = userRepository.save(userEntity);

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
        List<UserSettingEntity> userSettingEntityList = userEntityResponse.getUserSettings();
        for(UserSettingEntity userSettingEntity: userSettingEntityList){
            String keyName = userSettingEntity.getKeyName();
            if(keyName.equals(UserSettingEnum.BIOMETRIC_LOGIN.getKey())){
                userSettingResponseList.add(new BiometricLoginDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.PUSH_NOTIFICATION.getKey())){
                userSettingResponseList.add(new PushNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SHOW_ONBOARDING.getKey())){
                userSettingResponseList.add( new ShowOnboardingDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.SMS_NOTIFICATION.getKey())){
                userSettingResponseList.add(new SmsNotificationDto(userSettingEntity.getValueName()));
            }
            if (keyName.equals(UserSettingEnum.WIDGET_ORDER.getKey())){
                userSettingResponseList.add(new WidgetOrderDto(userSettingEntity.getValueName()));
            }
        }

        responseDto.setUserData(userDataDto);
        responseDto.setUserSetting(userSettingResponseList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
