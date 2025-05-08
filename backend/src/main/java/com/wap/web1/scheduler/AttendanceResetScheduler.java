package com.wap.web1.scheduler;


import com.wap.web1.repository.StudyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AttendanceResetScheduler {

    private  final StudyMemberRepository studyMemberRepository;

    @Scheduled(cron = "0 0 0 * * MON")//매주 월요일 00:00
    @Transactional
    public void resetWeeklyAttendance(){
        studyMemberRepository.resetAllweeklyAttendance();
    }
}
