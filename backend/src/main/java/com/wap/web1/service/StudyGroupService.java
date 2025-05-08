package com.wap.web1.service;

import com.wap.web1.domain.Attendance;
import com.wap.web1.domain.StudyGroup;
import com.wap.web1.domain.StudyMember;
import com.wap.web1.domain.User;
import com.wap.web1.dto.AttendanceCalendarDto;
import com.wap.web1.dto.GroupMembersDto;
import com.wap.web1.dto.GroupNoticeDto;
import com.wap.web1.dto.StudyGroupCreateDto;
import com.wap.web1.repository.AttendanceRepository;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.repository.StudyMemberRepository;
import com.wap.web1.repository.UserRepository;
import com.wap.web1.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyGroupService {
    private final AttendanceRepository attendanceRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final UserRepository userRepository;
    private final StudyMemberRepository studyMemberRepository;
    @Transactional
    public Response createStudyGroup(StudyGroupCreateDto dto, Long userId){
        if (studyGroupRepository.findByName(dto.getName()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 그룹명입니다.");
        }

        User leader = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("리더 유저를 찾을 수 없습니다."));

        //스터디 그룹 생성
        StudyGroup group = StudyGroup.builder()
                .name(dto.getName())
                .maxMembers(dto.getMaxMembers())
                .leader(leader)
                .notice(dto.getNotice())
                .meetingDays(dto.getMeetingDays())
                .meetingTime(dto.getMeetingTime())
                .meetingType(dto.getMeetingType())
                .region(dto.getRegion())
                .category(dto.getCategory())
                .type(dto.getType())
                .build();

        studyGroupRepository.save(group);

        //StudyMember에 리더 등록
        StudyMember leaderMember = StudyMember.builder()
                .studyGroup(group)
                .user(leader)
                .role(StudyMember.Role.LEADER)
                .status(StudyMember.Status.ACTIVE)
                .progress(0)
                .build();

        studyMemberRepository.save(leaderMember);

        return new Response("스터디 정보가 성공적으로 생성되었습니다.");
    }

    @Transactional(readOnly = true)
    public GroupMembersDto getStudyGroupMembers(Long studyGroupId) {
        StudyGroup group = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new IllegalArgumentException("스터디그룹을 찾을 수 없습니다"));

        List<StudyMember> members = studyMemberRepository.findByStudyGroupId(studyGroupId);

        List<GroupMembersDto.MemberDto> memberDtos = members.stream()
                .filter(member -> member.getStatus() == StudyMember.Status.ACTIVE)
                .map(member -> GroupMembersDto.MemberDto.builder()
                        .userId(member.getUser().getId())
                        .nickname(member.getUser().getNickname())
                        .profileImage(member.getUser().getProfileImage())
                        .build())
                .collect(Collectors.toList());

        return GroupMembersDto.builder()
                .studyGroupId(group.getId())
                .members(memberDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public GroupNoticeDto getNotice(Long studyGroupId){
        StudyGroup group = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(()->new IllegalArgumentException("스터디 그룹을 찾을 수 없습니다."));
        return new GroupNoticeDto(group.getId(),group.getNotice());
    }

    @Transactional
    public void takeAttendance(Long studyGroupId, Long userId) {
        StudyGroup group = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new IllegalArgumentException("스터디그룹을 찾을 수 없습니다"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

        StudyMember member = studyMemberRepository.findByStudyGroupAndUser(group, user)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디 그룹의 멤버가 아닙니다."));

        LocalDate today= LocalDate.now();

        boolean alreadyChecked = attendanceRepository.existsByStudyGroupAndUserAndDate(group, user, today);
        if(alreadyChecked){
            throw new IllegalArgumentException("이미 오늘 출석을 완료했습니다.");
        }

        //출석 저장
        Attendance attendance = Attendance.builder()
                .studyGroup(group)
                .user(user)
                .date(today)
                .status(Attendance.Status.PRESENT)
                .build();
        attendanceRepository.save(attendance);

        //출석 횟수 증가
        member.setAttendanceCount(member.getAttendanceCount() + 1);
    }

    @Transactional(readOnly = true)
    public List<AttendanceCalendarDto> getMonthlyAttendance(Long studyGroupId, Long userId, int year, int month){
        StudyGroup group = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new IllegalArgumentException("스터디그룹을 찾을 수 없습니다"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        List<Attendance> attendances = attendanceRepository.findByStudyGroupAndUserAndMonth(group, user, year, month);

        return attendances.stream()
                .map(att -> new AttendanceCalendarDto(att.getDate(), att.getStatus()))
                .collect(Collectors.toList());
    }
}
