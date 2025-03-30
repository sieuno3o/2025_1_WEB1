package com.wap.web1.controller;

import com.wap.web1.dto.JoinDto;
import com.wap.web1.service.JoinService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/auth/join")
    public ResponseEntity<String> joinProcess(@Valid @RequestBody JoinDto joinDto, BindingResult bindingResult){

        // 데이터 검증 오류가 있을 경우
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                    errorMessages.append(error.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }

        return joinService.joinProcess(joinDto); //회원가입 처리

    }
}
