package by.b1.testing.service.mappers;

import by.b1.testing.repository.entity.File;
import by.b1.testing.service.dto.FileDto;
import by.b1.testing.service.utils.Mapper;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
  private final ModelMapper modelMapper;

  @Autowired
  public FileMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  /**
   * Converts {@code File} object to {@code FileDto} object
   *
   * @param file model object to convert
   * @return converted dto object
   */
  public FileDto convertToDto(File file) {
    return modelMapper.map(file, FileDto.class);
  }

  /**
   * Converts {@code File} list to {@code FileDto} list
   *
   * @param files model objects to convert
   * @return converted dto objects
   */
  public List<FileDto> convertToDto(List<File> files) {
    return Mapper.convertList(files, this::convertToDto);
  }
}
