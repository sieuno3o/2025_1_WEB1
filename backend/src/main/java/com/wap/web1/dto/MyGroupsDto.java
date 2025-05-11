package com.wap.web1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyGroupsDto {
    private Long id;
    private List<MyStudyGroupDto> studygroups;
}
