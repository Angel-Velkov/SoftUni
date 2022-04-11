package bg.softuni.mobilelele.model.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class LifecycleEventEntity extends BaseEntity {

    private LocalDateTime created;

    private LocalDateTime modified;

    @PrePersist
    private void beforeCreate() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void beforeModify() {
        this.modified = LocalDateTime.now();
    }
}
