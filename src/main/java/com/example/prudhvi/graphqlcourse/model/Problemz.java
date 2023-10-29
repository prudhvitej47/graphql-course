package com.example.prudhvi.graphqlcourse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problemz")
@Getter
@Setter
public class Problemz {
    @Id
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String title;

    private String content;

    private String tags;

    @OneToMany(mappedBy = "problem")
    private List<Solutionz> solutions;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;
}
