package com.wap.web1.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyGroupCreateDto {
    @Size(max = 20, message = "그룹명은 최대 20자까지 입력할 수 있습니다.")
    private String name;
    private Integer maxMembers;
    @Size(max = 1000, message = "공지사항은 최대 1000자까지 입력할 수 있습니다.")
    private String notice;
    private String meetingDays;
    private String meetingTime;
    private String meetingType;
    private String department;
}
