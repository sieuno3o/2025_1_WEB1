package com.wap.web1.repository;

import com.wap.web1.domain.StudyGroup;
import com.wap.web1.domain.StudyMember;
import com.wap.web1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    //이미 해당 유저가 스터디에 가입 중인지 확인
    boolean existsByStudyGroupAndUserAndStatus(StudyGroup studyGroup, User user, StudyMember.Status status);

    //현재 ACTIVE 상태인 유저 수 확인(정원 초과 체크용)
    long countByStudyGroupAndStatus(StudyGroup studyGroup, StudyMember.Status status);

    List<StudyMember> findByUserId(Long userId);

}
