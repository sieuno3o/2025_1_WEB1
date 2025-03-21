package com.wap.web1.controller;

import com.wap.web1.domain.StudyGroup;
import com.wap.web1.dto.StudyGroupCreateDto;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.response.Response;
import com.wap.web1.security.CurrentUser;
import com.wap.web1.security.CustomUserDetails;
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
            @RequestParam Long userId // 임시로 userId를 직접 받음 나중에 삭제할 코드
            // POST /api/studygroup/create?userId=123이런식으로 테스트

            //,@CurrentUser CustomUserDetails currentUser // 인증 완성되면 이걸로 변경
    ){
        // 인증 적용 전이므로, 현재는 @RequestParam으로 받은 userId 사용
        // Long userId = currentUser.getUser().getId(); // 인증 적용 후 이 코드로 변경

        Response response = studyGroupService.createStudyGroup(dto, userId);
        return ResponseEntity.ok(response);
    }
}
