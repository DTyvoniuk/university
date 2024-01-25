package com.university.lector.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.university.lector.department.entity.LectorDepartment;
import com.university.lector.dto.Degree;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class Lector implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String fullName;

  @Enumerated(value = EnumType.STRING)
  @Default
  private Degree degree = Degree.ASSISTANT;

  @OneToMany(mappedBy = "lector")
  @JsonBackReference
  private List<LectorDepartment> lectorDepartments;

  @CreationTimestamp
  @Column(updatable = false)
  private Instant creationTimestamp;
}
