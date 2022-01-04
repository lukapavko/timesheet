package com.example.tasks.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;
import static javax.persistence.GenerationType.SEQUENCE;
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name="taskUser")
public class Task {

    @Id
    @SequenceGenerator(
            name="taskUser_sequence",
            sequenceName = "taskUser_sequence",
            allocationSize = 1 //povećavaj
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "taskUser_sequence"

    )
    @Column(name = "id", updatable = false)
    private long id;

    @NotNull
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "taskName")
    private String taskName;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date start_time;

    @UpdateTimestamp
    @Column(name = "end_time")
    private Date end_time;

    private OffsetDateTime start_end;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(user_id, task.user_id) && Objects.equals(taskName, task.taskName) && Objects.equals(start_time, task.start_time) && Objects.equals(end_time, task.end_time) && Objects.equals(start_end, task.start_end) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, taskName, start_time, end_time, start_end, description);
    }
}
