package com.university.department.controller;

import com.university.department.dto.DepartmentStatistic;
import com.university.department.entity.Department;
import com.university.department.service.DepartmentService;
import com.university.lector.department.service.LectorDepartmentService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentService departmentService;
  private final LectorDepartmentService lectorDepartmentService;

  @PostMapping("/create")
  public Department create(@RequestBody String departmentName) {
    return departmentService.create(departmentName);
  }

  @GetMapping("/{departmentId}/statistic")
  public DepartmentStatistic getStatisticById(@PathVariable UUID departmentId) {
    return lectorDepartmentService.getStatisticByDepartmentId(departmentId);
  }

  @GetMapping("/all/statistic")
  public List<DepartmentStatistic> getAllDepartmentsWithStatistic() {
    return lectorDepartmentService.getStatisticByDepartmentIds();
  }
}
