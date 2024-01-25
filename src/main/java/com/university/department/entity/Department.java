package com.university.department.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.university.lector.department.entity.LectorDepartment;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Department implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;

  @OneToMany(mappedBy = "department")
  @JsonBackReference
  private List<LectorDepartment> lectorDepartments;

  @CreationTimestamp
  @Column(updatable = false)
  private Instant creationTimestamp;
}
