package com.lingo.testservice.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "media_resources")
public class MediaResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "media_url")
    String mediaUrl;
    @Nullable
    String description;
    @OneToOne
    @JoinColumn(name = "test_id")
    Test test;
    @OneToOne
    @JoinColumn(name = "question_id")
    Question question;
}
