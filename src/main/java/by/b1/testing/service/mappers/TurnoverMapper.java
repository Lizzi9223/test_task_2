package by.b1.testing.service.mappers;

import by.b1.testing.repository.entity.Turnover;
import by.b1.testing.service.dto.TurnoverDto;
import by.b1.testing.service.utils.Mapper;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converts Turnover entity to Turnover DTO
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Component
public class TurnoverMapper {
  private final ModelMapper modelMapper;

  @Autowired
  public TurnoverMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  /**
   * Converts {@code Turnover} object to {@code TurnoverDto} object
   *
   * @param turnover model object to convert
   * @return converted dto object
   */
  public TurnoverDto convertToDto(Turnover turnover) {
    return modelMapper.map(turnover, TurnoverDto.class);
  }

  /**
   * Converts {@code Turnover} list to {@code TurnoverDto} list
   *
   * @param turnovers model objects to convert
   * @return converted dto objects
   */
  public List<TurnoverDto> convertToDto(List<Turnover> turnovers) {
    return Mapper.convertList(turnovers, this::convertToDto);
  }
}
