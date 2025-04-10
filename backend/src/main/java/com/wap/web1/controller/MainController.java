package com.wap.web1.controller;

import com.wap.web1.comfig.CurrentUser;
import com.wap.web1.dto.CustomUserDetails;
import com.wap.web1.response.Response;
import com.wap.web1.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @PostMapping("/{study_group_id}/join")
    public ResponseEntity<Response> joinStudyGroup(
            @PathVariable("study_group_id") Long studyGroupId,
            @CurrentUser CustomUserDetails currentUser
    ){
        Long userId = currentUser.getUser().getId();
        Response response = mainService.joinStudyGroup(studyGroupId,userId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("서버 돌아가는중");
    }


}
