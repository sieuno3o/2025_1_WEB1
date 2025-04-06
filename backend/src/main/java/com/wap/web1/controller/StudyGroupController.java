package com.wap.web1.controller;

import com.wap.web1.comfig.CurrentUser;
import com.wap.web1.dto.CustomUserDetails;
import com.wap.web1.dto.StudyGroupCreateDto;
import com.wap.web1.response.Response;
import com.wap.web1.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studygroup")
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @PostMapping("/create")
    public ResponseEntity<Response> createStudyGroup(
            @RequestBody StudyGroupCreateDto dto,
            @CurrentUser CustomUserDetails currentUser
    ){
        Long userId = currentUser.getUser().getId();

        Response response = studyGroupService.createStudyGroup(dto, userId);
        return ResponseEntity.ok(response);
    }
}
