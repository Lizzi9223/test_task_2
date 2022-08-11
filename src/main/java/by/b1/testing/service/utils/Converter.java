package by.b1.testing.service.utils;

import by.b1.testing.repository.entity.Class;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Utility class to convert one objects to another ones */
public class Converter {

  /**
   * converts string to Class entity
   *
   * @param string string to convert
   * @return formed object
   */
  public static Class stringToClassObject(String string) {
    String[] strings = string.trim().split("\\s+", 3);
    return new Class(Long.valueOf(strings[1]), strings[2]);
  }

  /**
   * extracts dates (time range) from string
   *
   * @param string string to extract from
   * @return extracted dates
   */
  public static LocalDate[] extractTimeRangeFromString(String string) {
    String[] strings = string.trim().split("\\s+");
    LocalDate[] dates = new LocalDate[2];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int idx = 0, i = 0; idx < dates.length && i < strings.length; i++) {
      try {
        dates[idx] = LocalDate.parse(strings[i], formatter);
        idx++;
      } catch (DateTimeParseException e) {
        System.out.println(e.getMessage());
      }
    }
    return dates;
  }
}
