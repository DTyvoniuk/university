package com.university.lector.controller;

import com.university.lector.entity.Lector;
import com.university.lector.service.LectorService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/lector")
@RequiredArgsConstructor
public class LectorController {

  private final LectorService lectorService;

  @PostMapping("/create")
  public Lector create(@RequestBody String lectorFullName) {
    return lectorService.createLector(lectorFullName);
  }

  @GetMapping("/search")
  public List<Lector> searchByQuery(@RequestParam(name = "query") String query) {
    return lectorService.getBySearchQuery(query);
  }

  @PutMapping("/{lectorId}/promote")
  public Lector promote(@PathVariable UUID lectorId) {
    return lectorService.promote(lectorId);
  }

  @GetMapping("/all")
  public List<Lector> getAllLectors() {
    return lectorService.getAllLectors();
  }

 /* @PostMapping("/{lectorId}/assign/{departmentId}")
  public LectorDepartment assignLectorToDepartment(@PathVariable UUID lectorId, @PathVariable UUID departmentId) {
    return lectorDepartmentRepository.save(LectorDepartment.builder()
        .key(LectorDepartmentKey.builder().departmentId(departmentId).lectorId(lectorId).build()).build());
  }

  @GetMapping("/{lectorId}")
  public List<LectorDepartment> getByLectorId(@PathVariable UUID lectorId) {
    return lectorDepartmentRepository.findAllByKeyLectorId(lectorId);
  }*/
}
