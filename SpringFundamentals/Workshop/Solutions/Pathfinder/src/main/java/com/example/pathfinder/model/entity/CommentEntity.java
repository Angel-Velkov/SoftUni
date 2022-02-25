package com.example.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private Boolean approved;

    private LocalDateTime created;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne
    private RouteEntity route;

    @PrePersist
    private void setCreated() {
        this.created = LocalDateTime.now();
    }
}
