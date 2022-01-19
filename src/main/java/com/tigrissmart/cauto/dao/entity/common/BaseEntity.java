package com.tigrissmart.cauto.dao.entity.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Getter
@Setter
@MappedSuperclass
public class BaseEntity  implements Serializable {
    @CreatedBy
    @Column(name = "CreatedBy")
    private String createdBy="anonim";

    @CreatedDate
    @ReadOnlyProperty
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    private Date createdAt;

    @LastModifiedBy
    @Column(name = "ModifiedBy")
    private String modifiedBy="anonim";

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedAt")
    private Date modifiedAt;
    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.modifiedAt == null) modifiedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.modifiedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.modifiedAt = new Date();
    }


}
