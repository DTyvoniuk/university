package com.university.lector.department.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.university.department.entity.Department;
import com.university.lector.entity.Lector;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LectorDepartment implements Serializable {

  @EmbeddedId
  private LectorDepartmentKey key;

  @ManyToOne
  @JoinColumn(name = "lector_id", insertable = false, updatable = false)
  @JsonManagedReference
  private Lector lector;

  @ManyToOne
  @JoinColumn(name = "department_id", insertable = false, updatable = false)
  @JsonManagedReference
  private Department department;

  @CreationTimestamp
  @Column(updatable = false)
  private Instant creationTimestamp;

  @Embeddable
  @Getter
  @Setter
  @Builder
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  public static class LectorDepartmentKey implements Serializable {

    @Column(name = "lector_id")
    private UUID lectorId;

    @Column(name = "department_id")
    private UUID departmentId;
  }
}
