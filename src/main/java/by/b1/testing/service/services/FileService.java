package by.b1.testing.service.services;

import by.b1.testing.repository.repos.FileRepository;
import by.b1.testing.service.dto.FileDto;
import by.b1.testing.service.mappers.FileMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Contains methods to work with file repository
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Service
@ComponentScan("by.b1.testing")
public class FileService {
  private final FileRepository fileRepository;
  private final FileMapper fileMapper;

  @Autowired
  public FileService(FileRepository fileRepository, FileMapper fileMapper) {
    this.fileRepository = fileRepository;
    this.fileMapper = fileMapper;
  }

  public List<FileDto> getImportedFiles(){
    return fileMapper.convertToDto(fileRepository.findAll());
  }
}
