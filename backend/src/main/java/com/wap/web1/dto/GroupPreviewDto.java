package com.wap.web1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupPreviewDto {

    private Long studyGroupId;

    private String name;
}
