package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.dto.UserDto;
import com.example.simpleProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseDto<UserDto> create(@Valid @RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @GetMapping("/get")
    public ResponseDto<UserDto> get(@RequestParam Integer userId){
        return userService.get(userId);
    }

    @PutMapping("/update")
    public ResponseDto<UserDto> update(@RequestBody UserDto userDto, @RequestParam Integer userId){
        return userService.update(userDto, userId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<UserDto> delete(@RequestParam Integer userId){
        return userService.delete(userId);
    }

}
