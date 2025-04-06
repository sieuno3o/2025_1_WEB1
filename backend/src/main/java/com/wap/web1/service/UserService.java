package com.wap.web1.service;


import com.wap.web1.domain.StudyGroup;
import com.wap.web1.domain.StudyMember;
import com.wap.web1.domain.User;
import com.wap.web1.dto.GroupPreviewDto;
import com.wap.web1.dto.MyGroupsDto;
import com.wap.web1.dto.MyInfoDto;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.repository.StudyMemberRepository;
import com.wap.web1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

    private final StudyMemberRepository studyMemberRepository;

    public MyInfoDto getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return MyInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .build();
    }

    public MyGroupsDto getMyGroups(Long userId){
        List<StudyMember> memberships = studyMemberRepository.findByUserId(userId);

        List<GroupPreviewDto> studyGroups = memberships.stream()
                .map(member -> {
                    StudyGroup group = member.getStudyGroup();
                    return new GroupPreviewDto(group.getId(),group.getName());
                })
                .collect(Collectors.toList());
        return new MyGroupsDto(userId, studyGroups);
    }
}
