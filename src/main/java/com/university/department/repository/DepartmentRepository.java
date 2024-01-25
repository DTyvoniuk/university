package com.university.department.repository;

import com.university.department.entity.Department;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Department.class, idClass = UUID.class)
public interface DepartmentRepository {

  Department save(Department department);

  Optional<Department> findById(UUID departmentId);

  @Query(value = """
   SELECT
    department
   FROM
     Department department
    LEFT JOIN
      LectorDepartment ld
    ON
      department.id = ld.key.departmentId
    WHERE
     ld IS NULL
  """)
  List<Department> getDepartmentWithoutLectors();
}
