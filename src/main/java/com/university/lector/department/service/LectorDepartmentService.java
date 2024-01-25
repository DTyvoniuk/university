package com.university.lector.department.service;

import com.university.department.dto.DepartmentStatistic;
import com.university.department.entity.Department;
import com.university.department.exception.DepartmentException;
import com.university.department.service.DepartmentService;
import com.university.lector.department.entity.LectorDepartment;
import com.university.lector.department.entity.LectorDepartment.LectorDepartmentKey;
import com.university.lector.department.repository.LectorDepartmentRepository;
import com.university.lector.dto.Degree;
import com.university.lector.entity.Lector;
import com.university.lector.service.LectorService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectorDepartmentService {

  private record DepartmentDegreeKey(UUID departmentId, Degree degree) {
  }

  private final LectorDepartmentRepository lectorDepartmentRepository;
  private final DepartmentService departmentService;
  private final LectorService lectorService;

  public DepartmentStatistic getStatisticByDepartmentId(UUID departmentId) {
    Optional<Department> departmentOptional = departmentService.getById(departmentId);
    if (departmentOptional.isEmpty()) {
      throw new DepartmentException(String.format("Not found department with id: %s", departmentId));
    }
    return getStatisticByDepartmentIds().stream()
        .filter(departmentStatistic -> departmentId.equals(departmentStatistic.id()))
        .findFirst()
        .orElseGet(
            () -> DepartmentStatistic.of(
                departmentId,
                departmentOptional.get().getName(),
                0L,
                0L,
                0L,
                Collections.emptyList(),
                Collections.emptyList()
            )
        );
  }

  public List<DepartmentStatistic> getStatisticByDepartmentIds() {
    List<LectorDepartment> lectorDepartments = lectorDepartmentRepository.findAll();
    Map<DepartmentDegreeKey, Long> statisticByKey = lectorDepartments.stream()
            .collect(Collectors.groupingBy(
                lectorDepartment -> new DepartmentDegreeKey(lectorDepartment.getKey().getDepartmentId(),
                    lectorDepartment.getLector().getDegree()), Collectors.counting()
            ));
    Map<UUID, List<Lector>> lectorsByDepartmentId = lectorDepartments.stream()
        .collect(Collectors.groupingBy(
            lectorDepartment -> lectorDepartment.getKey().getDepartmentId(),
            Collectors.mapping(LectorDepartment::getLector, Collectors.toList())
        ));
    List<Lector> allLectors = lectorService.getAllLectors();
    List<Department> departmentWithoutLectors = departmentService.getDepartmentWithoutLectors();
    Set<Department> departments = lectorDepartments.stream()
        .map(LectorDepartment::getDepartment)
        .collect(Collectors.toSet());
    departments =  Stream.of(departments, departmentWithoutLectors)
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    return departments.stream()
        .map(department -> mapToDepartmentStatistic(
            department, statisticByKey, lectorsByDepartmentId, allLectors
        ))
        .collect(Collectors.toList());
  }

  private DepartmentStatistic mapToDepartmentStatistic(
      Department department,
      Map<DepartmentDegreeKey,
      Long> statisticByKey,
      Map<UUID, List<Lector>> lectorsByDepartmentId,
      List<Lector> allLector
  ) {
    UUID departmentId = department.getId();
    List<Lector> lectorsInDepartment = lectorsByDepartmentId.getOrDefault(departmentId, Collections.emptyList());
    List<Lector> notAssignedLectors = allLector.stream()
        .filter(Predicate.not(lectorsInDepartment::contains))
        .toList();
    return DepartmentStatistic.of(
        departmentId,
        department.getName(),
        statisticByKey.getOrDefault(new DepartmentDegreeKey(departmentId, Degree.ASSISTANT), 0L),
        statisticByKey.getOrDefault(new DepartmentDegreeKey(departmentId, Degree.ASSOCIATE_PROFESSOR), 0L),
        statisticByKey.getOrDefault(new DepartmentDegreeKey(departmentId, Degree.PROFESSOR), 0L),
        lectorsInDepartment,
        notAssignedLectors
    );
  }

  public LectorDepartment assignLectorToDepartment(UUID lectorId, UUID departmentId) {
    return lectorDepartmentRepository.save(
        LectorDepartment.builder()
            .key(LectorDepartmentKey.builder()
                .departmentId(departmentId)
                .lectorId(lectorId)
                .build())
            .build());
  }

  public void unassignLectorFromDepartment(UUID lectorId, UUID departmentId) {
    lectorDepartmentRepository.deleteByKey(
        LectorDepartmentKey.builder()
            .departmentId(departmentId)
            .lectorId(lectorId)
            .build()
    );
  }
}
