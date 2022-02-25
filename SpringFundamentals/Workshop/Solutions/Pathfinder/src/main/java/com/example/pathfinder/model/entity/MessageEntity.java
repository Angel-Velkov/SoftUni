package com.example.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {

    private LocalDateTime dateTime;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne(optional = false)
    private UserEntity recipient;
}
