package com.wap.web1.repository;

import com.wap.web1.domain.StudyGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository<StudyGroup,Long> {
    Optional<StudyGroup> findByName(String name);

    @Query("SELECT sg FROM StudyGroup sg " +
            "WHERE sg.id > :cursor "+
            "AND sg.maxMembers > (" +
            "    SELECT COUNT(sm) FROM StudyMember sm " +
            "    WHERE sm.studyGroup = sg AND sm.status = 'ACTIVE'" +
            ") " +
            "ORDER BY sg.id ASC")
    List<StudyGroup> findByCursor(Long cursor, Pageable pageable);

}
