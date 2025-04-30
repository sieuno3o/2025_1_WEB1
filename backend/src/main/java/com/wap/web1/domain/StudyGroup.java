package com.wap.web1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "study_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    private Integer maxMembers;

    @ManyToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @Column(length = 1000)
    private String notice;

    private String meetingDays;

    @Column(length = 10)
    private String meetingTime;

    private String meetingType;

    private String department;
}
