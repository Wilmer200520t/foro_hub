package com.forohub.topic.models;

import com.forohub.general.models.Course;
import com.forohub.general.models.Gender;
import com.forohub.general.models.Status;
import com.forohub.user.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "topic")
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Course course;

    @Column(columnDefinition = "DATETIME2 DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime creationDate;

    @Column(columnDefinition = "DATETIME2 DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime modificationDate;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = Status.Active;
        }
    }
}
