package com.example.prudhvi.graphqlcourse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "userz_token")
@Getter
@Setter
public class UserzToken {
    @Id
    private UUID id;

    private String authToken;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime expiryAt;
}
