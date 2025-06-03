package com.wap.web1.dto;

import com.wap.web1.domain.Category;
import com.wap.web1.domain.RecruitStatus;
import com.wap.web1.domain.Region;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupUpdateDto {
    private String name;
    private Integer maxMembers;
    private String notice;
    private String meetingDays;
    private String meetingTime;
    private String meetingType;
    private Region region;
    private Category category;
    private String type;
    private LocalDate startDate;
    private RecruitStatus recruitStatus;
}
