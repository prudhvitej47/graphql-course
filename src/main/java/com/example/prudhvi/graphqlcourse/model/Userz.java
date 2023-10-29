package com.example.prudhvi.graphqlcourse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "userz")
@Getter
@Setter
public class Userz {
    @Id
    private UUID id;

    private String username;

    private String email;

    private String hashedPassword;

    private URL avatar;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String displayName;

    private boolean active;
}
