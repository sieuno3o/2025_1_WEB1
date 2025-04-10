package com.wap.web1.repository;

import com.wap.web1.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository<StudyGroup,Long> {
    Optional<StudyGroup> findByName(String name);
}
