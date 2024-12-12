package org.ecommerce.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "lastModifiedBy", "deletedBy"}
)
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityBase implements Serializable {

    @Column(
            nullable = false,
            updatable = false,
            name = "created_at"
    )
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "last_modified_at")
    private Timestamp lastModifiedAt;
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

}
