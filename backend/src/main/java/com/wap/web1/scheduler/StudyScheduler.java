package com.wap.web1.scheduler;


import com.wap.web1.domain.RecruitStatus;
import com.wap.web1.domain.StudyGroup;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.repository.StudyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudyScheduler {

    private  final StudyMemberRepository studyMemberRepository;
    private  final StudyGroupRepository studyGroupRepository;

    // 출석 리셋
    @Scheduled(cron = "0 0 0 * * MON")//매주 월요일 00:00
    @Transactional
    public void resetWeeklyAttendance(){
        studyMemberRepository.resetAllweeklyAttendance();
    }

    // 모집 상태 자동 마감
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    @Transactional
    public void autoCloseRecruitingStudyGroups() {
        LocalDate today = LocalDate.now();
        List<StudyGroup> recruitingGroups = studyGroupRepository.findByRecruitStatus(RecruitStatus.RECRUITING);

        for (StudyGroup group : recruitingGroups) {
            if (!group.getStartDate().isAfter(today)) {
                group.setRecruitStatus(RecruitStatus.CLOSED);
            }
        }

        studyGroupRepository.saveAll(recruitingGroups);
    }
}
