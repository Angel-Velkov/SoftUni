package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@MappedSuperclass
public abstract class LifecycleEventEntity extends BaseEntity {

    @Column(nullable = false)
    private Instant created;

    private Instant modified;

    @PrePersist
    private void beforeCreate() {
        this.created = Instant.now();
    }

    @PreUpdate
    private void beforeModify() {
        this.modified = Instant.now();
    }
}
