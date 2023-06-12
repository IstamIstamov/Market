package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.dto.UserDto;
import com.example.simpleProject.model.User;
import com.example.simpleProject.repository.UserRepository;
import com.example.simpleProject.service.mapper.ImageMapper;
import com.example.simpleProject.service.mapper.LoanerMapper;
import com.example.simpleProject.service.mapper.UserMapper;
import com.example.simpleProject.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation userValidation;/*
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final LoanerService loanerService;
    private final LoanerMapper loanerMapper;*/

    public ResponseDto<UserDto> create(UserDto userDto) {
        List<ErrorDto> errors = userValidation.validate(userDto);
        if (!errors.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(userDto)
                    .errors(errors)
                    .build();
        }
        try {
            User user = userMapper.toEntity(userDto);
            user.setCreateAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(userMapper.toDto(user))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<UserDto> get(Integer userId) {
        try{
            Optional<User> optional = userRepository.findByUserIdAndDeleteAtIsNull(userId);
            if (optional.isEmpty()){
                return ResponseDto.<UserDto>builder()
                        .success(false)
                        .code(-1)
                        .message("User is not found")
                        .build();
            }
            /*optional.get().setImage(imageMapper.toEntity(imageService.get(userId).getData()));
            optional.get().setLoaner(loanerMapper.toEntity(loanerService.get(userId).getData()));*/
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(userMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<UserDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<UserDto> update(UserDto userDto, Integer userId) {
        List<ErrorDto> errors = userValidation.validate(userDto);
        if (!errors.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(userDto)
                    .errors(errors)
                    .build();
        }
        Optional<User> optional = userRepository.findByUserIdAndDeleteAtIsNull(userId);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .success(false)
                    .code(-1)
                    .message("User is not found")
                    .build();
        }
        try {
            User user = userMapper.toEntity(userDto);
            user.setUpdateAt(LocalDateTime.now());
            this.userMapper.updateUserFromDto(userDto, user);
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.userMapper.toDto(user))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<UserDto> delete(Integer userId) {
        Optional<User> optional = userRepository.findByUserIdAndDeleteAtIsNull(userId);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User is not found")
                    .build();
        }
        try {
            User user = optional.get();
            user.setDeleteAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(userMapper.toDto(user)).build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
