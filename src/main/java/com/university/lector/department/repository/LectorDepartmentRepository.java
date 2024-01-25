package com.university.lector.department.repository;

import com.university.lector.department.entity.LectorDepartment;
import com.university.lector.department.entity.LectorDepartment.LectorDepartmentKey;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = LectorDepartment.class, idClass = LectorDepartmentKey.class)
public interface LectorDepartmentRepository {

  LectorDepartment save(LectorDepartment lectorDepartment);

  List<LectorDepartment> findAll();

  @Modifying
  void deleteByKey(LectorDepartmentKey lectorDepartmentKey);
}
