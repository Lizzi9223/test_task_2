package by.b1.testing.service.mappers;

import by.b1.testing.repository.entity.Balance;
import by.b1.testing.service.dto.BalanceDto;
import by.b1.testing.service.utils.Mapper;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converts Balance entity to Balance DTO
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Component
public class BalanceMapper {
  private final ModelMapper modelMapper;

  @Autowired
  public BalanceMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  /**
   * Converts {@code Balance} object to {@code BalanceDto} object
   *
   * @param balance model object to convert
   * @return converted dto object
   */
  public BalanceDto convertToDto(Balance balance) {
    return modelMapper.map(balance, BalanceDto.class);
  }

  /**
   * Converts {@code Balance} list to {@code BalanceDto} list
   *
   * @param balances model objects to convert
   * @return converted dto objects
   */
  public List<BalanceDto> convertToDto(List<Balance> balances) {
    return Mapper.convertList(balances, this::convertToDto);
  }
}
