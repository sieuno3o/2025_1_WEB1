package com.wap.web1.repository;

import com.wap.web1.domain.StudyRanking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRankingRepository extends JpaRepository<StudyRanking, Long> {
    List<StudyRanking> findByStudyMember_StudyGroupId(Long studyGroupId);
}
