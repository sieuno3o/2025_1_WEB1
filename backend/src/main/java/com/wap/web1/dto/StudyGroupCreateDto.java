package com.wap.web1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyGroupCreateDto {
    private String name;
    private Integer maxMembers;
    private String notice;
    private String meetingDays;
    private String meetingTime;
}
