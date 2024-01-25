package com.university.lector.service;

import static com.university.lector.dto.Degree.ASSISTANT;
import static com.university.lector.dto.Degree.PROFESSOR;

import com.university.lector.dto.Degree;
import com.university.lector.entity.Lector;
import com.university.lector.exception.LectorException;
import com.university.lector.repository.LectorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectorService {

  private final LectorRepository lectorRepository;

  public Lector createLector(String lectorFullName) {
    String formattedLectorFullName = lectorFullName.replace("\"", "");
    return save(Lector.builder().fullName(formattedLectorFullName).degree(ASSISTANT).build());
  }

  public Lector save(Lector lector) {
    return lectorRepository.save(lector);
  }

  public List<Lector> getBySearchQuery(String searchQuery) {
    return lectorRepository.getLectorsBySearchQuery(searchQuery.toLowerCase());
  }

  public Lector promote(UUID lectorId) {
    Optional<Lector> lectorOptional = lectorRepository.findById(lectorId);
    if (lectorOptional.isEmpty()) {
      throw new LectorException(String.format("Not found lector by id: %s", lectorId));
    }
    Lector lector = lectorOptional.get();
    if (PROFESSOR.equals(lector.getDegree())) {
      throw new LectorException(String.format("Lector with id %s already Professor", lectorId));
    }
    Degree nextDegree = Degree.getByOrdinal(lector.getDegree().getOrdinal() + 1);
    lector.setDegree(nextDegree);
    return save(lector);
  }

  public List<Lector> getAllLectors() {
    return lectorRepository.findAll();
  }
}
