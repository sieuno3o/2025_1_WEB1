package com.wap.web1.dto;

import com.wap.web1.domain.Attendance;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AttendanceCalendarDto {
    private LocalDate date;
    private Attendance.Status status;
}
