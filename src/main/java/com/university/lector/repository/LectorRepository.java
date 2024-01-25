package com.university.lector.repository;

import com.university.lector.entity.Lector;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = Lector.class, idClass = UUID.class)
public interface LectorRepository {

  Optional<Lector> findById(UUID lectorId);

  Lector save(Lector lector);

  @Query(value = """
    SELECT
      lector
    FROM
      Lector lector
    WHERE
      LOWER(lector.fullName) LIKE %:searchQuery%
  """)
  List<Lector> getLectorsBySearchQuery(@Param("searchQuery") String searchQuery);

  List<Lector> findAll();
}
