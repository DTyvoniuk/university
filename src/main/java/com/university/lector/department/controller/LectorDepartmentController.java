package com.university.lector.department.controller;

import com.university.lector.department.entity.LectorDepartment;
import com.university.lector.department.service.LectorDepartmentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lector/{lectorId}/department/{departmentId}")
public class LectorDepartmentController {

  private final LectorDepartmentService lectorDepartmentService;

  @PostMapping("/assign")
  public LectorDepartment assignLectorToDepartment(@PathVariable UUID lectorId, @PathVariable UUID departmentId) {
    return lectorDepartmentService.assignLectorToDepartment(lectorId, departmentId);
  }

  @DeleteMapping("/unassign")
  @Transactional
  public void unassignLectorToDepartment(@PathVariable UUID lectorId, @PathVariable UUID departmentId) {
    lectorDepartmentService.unassignLectorFromDepartment(lectorId, departmentId);
  }
}
