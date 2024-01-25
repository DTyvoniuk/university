package com.university.department.service;

import com.university.department.entity.Department;
import com.university.department.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentRepository departmentRepository;

  public Department create(String departmentName) {
    String formattedDepartmentName = departmentName.replace("\"", "");
    return save(Department.builder().name(formattedDepartmentName).build());
  }

  public Department save(Department department) {
    return departmentRepository.save(department);
  }

  public Optional<Department> getById(UUID departmentId) {
    return departmentRepository.findById(departmentId);
  }

  public List<Department> getDepartmentWithoutLectors() {
    return departmentRepository.getDepartmentWithoutLectors();
  }
}
