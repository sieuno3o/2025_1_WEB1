package com.wap.web1.controller;

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
        @RequestParam Long userId // 임시로 userId를 직접 받음 나중에 삭제할 코드
        // POST /api/studygroup/create?userId=123이런식으로 테스트
        //,@CurrentUser CustomUserDetails currentUser // 인증 완성되면 이걸로 변경
    ){
            // 인증 적용 전이므로, 현재는 @RequestParam으로 받은 userId 사용
            // Long userId = currentUser.getUser().getId(); // 인증 적용 후 이 코드로 변경
            Response response = mainService.joinStudyGroup(studyGroupId,userId);
            return ResponseEntity.ok(response);
        }


}
