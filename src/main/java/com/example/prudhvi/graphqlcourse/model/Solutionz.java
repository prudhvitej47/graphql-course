package com.example.prudhvi.graphqlcourse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solutionz")
@Getter
@Setter
public class Solutionz {
    @Id
    private UUID id;

    private LocalDateTime createdAt;

    private String content;

    private String category;

    private int voteGoodCount;

    private int voteBadCount;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;

    @ManyToOne
    @JoinColumn(name = "problemz_id", nullable = false)
    private Problemz problem;
}
