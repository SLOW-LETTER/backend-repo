package com.miniproject.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    @Column
    @CreatedDate
    private LocalDate created_at;

    @Column
    @LastModifiedDate
    private LocalDate updated_at;

    @PrePersist
    public void onPrePersist(){
        this.created_at = LocalDate.now();
        this.updated_at = this.updated_at;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.updated_at = LocalDate.now();
    }
}
