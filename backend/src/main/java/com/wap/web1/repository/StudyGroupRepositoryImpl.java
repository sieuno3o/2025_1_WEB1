package com.wap.web1.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wap.web1.domain.*;
import com.wap.web1.dto.StudyGroupWithMemberCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.wap.web1.domain.QStudyGroup.studyGroup;
import static com.wap.web1.domain.QStudyMember.studyMember;

@Repository
@RequiredArgsConstructor
public class StudyGroupRepositoryImpl implements StudyGroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyGroupWithMemberCountDto> findStudyGroupsWithMemberCount(Long cursor, int size) {
        return queryFactory
                .select(Projections.constructor(StudyGroupWithMemberCountDto.class,
                        studyGroup.id,
                        studyGroup.name,
                        studyGroup.meetingDays,
                        studyGroup.meetingTime,
                        studyGroup.meetingType,
                        studyMember.id.count().intValue(), // currentMembers
                        studyGroup.maxMembers,
                        studyGroup.region,
                        studyGroup.category,
                        studyGroup.type,
                        studyGroup.startDate
                ))
                .from(studyGroup)
                .leftJoin(studyMember).on(
                        studyMember.studyGroup.eq(studyGroup)
                                .and(studyMember.status.eq(StudyMember.Status.ACTIVE))
                )
                .where(
                        studyGroup.recruitStatus.eq(RecruitStatus.RECRUITING),
                        cursor != null ? studyGroup.id.gt(cursor) : null
                )
                .groupBy(studyGroup.id)
                .having(studyMember.count().lt(studyGroup.maxMembers))
                .orderBy(studyGroup.id.asc())
                .limit(size + 1)
                .fetch();
    }
}
