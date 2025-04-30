package com.wap.web1.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class StudyGroupResponse {
    private final List<StudyGroupDto> groups;
    private final Long nextCursor;
    private final String message;

    private StudyGroupResponse(List<StudyGroupDto> groups, Long nextCursor, String message) {
        this.groups = groups;
        this.nextCursor = nextCursor;
        this.message = message;
    }

    public static StudyGroupResponse of(List<StudyGroupDto> groups, Long nextCursor) {
        return new StudyGroupResponse(groups,nextCursor,null);
    }
    public static StudyGroupResponse of (String message) {
        return new StudyGroupResponse(null,null,message);
    }
}
