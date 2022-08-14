package by.b1.testing.service.dto;

import java.util.Objects;

/**
 * Data transfer object for file entity
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class FileDto {
  private Long id;

  private String name;

  public FileDto() {}

  public FileDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileDto fileDto = (FileDto) o;
    return Objects.equals(id, fileDto.id) && Objects.equals(name, fileDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "FileDto{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
