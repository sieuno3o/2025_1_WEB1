package com.wap.web1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyGroupDto {
    private long id;
    private String name;
    private String meetingDays;
    private String meetingTime;
    private String department;
    private String meetingType;
    private int currentMembers;
    private int maxMembers;
}
