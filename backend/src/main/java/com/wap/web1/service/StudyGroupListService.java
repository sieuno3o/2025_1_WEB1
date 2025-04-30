package com.wap.web1.service;

import com.wap.web1.domain.StudyGroup;
import com.wap.web1.domain.StudyMember;
import com.wap.web1.dto.StudyGroupDto;
import com.wap.web1.dto.StudyGroupResponse;
import com.wap.web1.repository.StudyGroupRepository;
import com.wap.web1.repository.StudyMemberRepository;
import com.wap.web1.util.ScrollPaginationCollection;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyGroupListService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyMemberRepository studyMemberRepository;

    public StudyGroupListService(StudyGroupRepository studyGroupRepository,StudyMemberRepository studyMemberRepository) {
        this.studyGroupRepository = studyGroupRepository;
        this.studyMemberRepository = studyMemberRepository;
    }

    public StudyGroupResponse getStudyGroups(Long cursor,int size) {
        PageRequest pageable = PageRequest.of(0, size + 1);

        List<StudyGroup> studyGroups = studyGroupRepository.findByCursor(cursor != null ? cursor : 0L, pageable);

        List<StudyGroupDto> dtos = studyGroups.stream()
                .map(studyGroup ->{
                    long currentMembers = studyMemberRepository.countByStudyGroupAndStatus(studyGroup, StudyMember.Status.ACTIVE);
                    return new StudyGroupDto(
                            studyGroup.getId(),
                            studyGroup.getName(),
                            studyGroup.getMeetingDays(),
                            studyGroup.getMeetingTime(),
                            studyGroup.getMeetingType(),
                            (int) currentMembers,
                            studyGroup.getMaxMembers(),
                            studyGroup.getRegion(),
                            studyGroup.getCategory(),
                            studyGroup.getType()

                    );
                })
                .collect(Collectors.toList());


        ScrollPaginationCollection<StudyGroupDto> scroll = ScrollPaginationCollection.of(dtos,size);
        return StudyGroupResponse.of(scroll.getCurrentScrollItems(),scroll.isLastScroll() ? null : scroll.getNextCursor().getId());
    }
}
