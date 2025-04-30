package com.wap.web1.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class StudyGroupResponse {
    private final List<StudyGroupDto> groups;
    private final Long nextCursor;

    private StudyGroupResponse(List<StudyGroupDto> groups, Long nextCursor) {
        this.groups = groups;
        this.nextCursor = nextCursor;
    }

    public static StudyGroupResponse of(List<StudyGroupDto> groups, Long nextCursor) {
        return new StudyGroupResponse(groups,nextCursor);
    }
}
