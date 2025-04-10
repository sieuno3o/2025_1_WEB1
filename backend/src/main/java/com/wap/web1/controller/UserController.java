package com.wap.web1.controller;

import com.wap.web1.comfig.CurrentUser;
import com.wap.web1.dto.CustomUserDetails;
import com.wap.web1.dto.MyGroupsDto;
import com.wap.web1.dto.MyInfoDto;
import com.wap.web1.dto.StudyGroupCreateDto;
import com.wap.web1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<MyInfoDto> getMyInfo(@CurrentUser CustomUserDetails currentUser) {
        Long userId = currentUser.getUser().getId();
        MyInfoDto response = userService.getMyInfo(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/studygroups")
    public ResponseEntity<MyGroupsDto> getMyGroups(@CurrentUser CustomUserDetails currentUser){
        Long userId = currentUser.getUser().getId();
        MyGroupsDto response = userService.getMyGroups(userId);
        return ResponseEntity.ok(response);
    }
}


