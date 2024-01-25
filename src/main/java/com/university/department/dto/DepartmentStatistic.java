package com.university.department.dto;

import com.university.lector.entity.Lector;
import java.util.List;
import java.util.UUID;

public record DepartmentStatistic(
    UUID id,
    String departmentName,
    Long assistantCount,
    Long associateProfessorCount,
    Long professorCount,
    List<Lector> assignedLectors,
    List<Lector> notAssignedLectors
) {

  public static DepartmentStatistic of(
      UUID departmentId,
      String departmentName,
      Long assistantCount,
      Long associateProfessorCount,
      Long professorCount,
      List<Lector> assignedLectors,
      List<Lector> notAssignedLectors
  ) {
    return new DepartmentStatistic(
        departmentId,
        departmentName,
        assistantCount,
        associateProfessorCount,
        professorCount,
        assignedLectors,
        notAssignedLectors
    );
  }
}
