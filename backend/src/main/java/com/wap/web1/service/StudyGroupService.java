package com.wap.web1.service;

import com.wap.web1.domain.StudyGroup;
import com.wap.web1.domain.User;
import com.wap.web1.dto.StudyGroupCreateDto;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.repository.UserRepository;
import com.wap.web1.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyGroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final UserRepository userRepository;

    public Response createStudyGroup(StudyGroupCreateDto dto, Long userId){
        if (studyGroupRepository.findByName(dto.getName()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 그룹명입니다.");
        }

        User leader = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("리더 유저를 찾을 수 없습니다."));

        StudyGroup group = StudyGroup.builder()
                .name(dto.getName())
                .maxMembers(dto.getMaxMembers())
                .leader(leader)
                .notice(dto.getNotice())
                .meetingDays(dto.getMeetingDays())
                .meetingTime(dto.getMeetingTime())
                .build();

        studyGroupRepository.save(group);

        return new Response("스터디 정보가 성공적으로 생성되었습니다.");
    }
}
