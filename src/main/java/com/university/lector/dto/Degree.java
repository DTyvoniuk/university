package com.university.lector.dto;

import com.university.lector.exception.LectorException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Degree {
  ASSISTANT(0),
  ASSOCIATE_PROFESSOR(1),
  PROFESSOR(2);

  private final int ordinal;

  public static Degree getByOrdinal(int ordinal) {
    return Arrays.stream(Degree.values())
        .filter(degree -> degree.getOrdinal() == ordinal)
        .findFirst()
        .orElseThrow(() -> new LectorException(String.format("Not found lector degree with ordinal %s", ordinal)));
  }
}
