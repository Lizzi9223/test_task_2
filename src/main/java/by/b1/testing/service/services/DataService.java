package by.b1.testing.service.services;

import by.b1.testing.repository.entity.Balance;
import by.b1.testing.repository.entity.Class;
import by.b1.testing.repository.entity.File;
import by.b1.testing.repository.entity.Turnover;
import by.b1.testing.repository.enums.BalanceType;
import by.b1.testing.repository.repos.BalanceRepository;
import by.b1.testing.repository.repos.ClassRepository;
import by.b1.testing.repository.repos.FileRepository;
import by.b1.testing.repository.repos.TurnoverRepository;
import by.b1.testing.service.dto.BalanceDto;
import by.b1.testing.service.dto.TurnoverDto;
import by.b1.testing.service.enums.Headers;
import by.b1.testing.service.mappers.BalanceMapper;
import by.b1.testing.service.mappers.TurnoverMapper;
import by.b1.testing.service.utils.Converter;
import by.b1.testing.service.utils.ExcelUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Contains methods to work with repositories
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Service
@ComponentScan("by.b1.testing")
public class DataService {
  private final ClassRepository classRepository;
  private final BalanceRepository balanceRepository;
  private final TurnoverRepository turnoverRepository;
  private final FileRepository fileRepository;
  private final BalanceMapper balanceMapper;
  private final TurnoverMapper turnoverMapper;
  private final ExcelUtils excelUtils;
  private Class bankClass = new Class();
  private Balance openBalance;
  private Balance closeBalance;
  private Turnover turnover;
  protected LocalDate from;
  protected LocalDate to;
  private File currentFile;

  @Autowired
  public DataService(
      ClassRepository classRepository,
      BalanceRepository balanceRepository,
      TurnoverRepository turnoverRepository,
      FileRepository fileRepository,
      BalanceMapper balanceMapper,
      TurnoverMapper turnoverMapper,
      ExcelUtils excelUtils) {
    this.classRepository = classRepository;
    this.balanceRepository = balanceRepository;
    this.turnoverRepository = turnoverRepository;
    this.fileRepository = fileRepository;
    this.balanceMapper = balanceMapper;
    this.turnoverMapper = turnoverMapper;
    this.excelUtils = excelUtils;
  }

  /**
   * gets balances by file name
   * @param name name of the file to find balances by
   * @return list containing all balances
   */
  public List<BalanceDto> getBalances(String name) {
    File file = fileRepository.findByName(name);
    List<Balance> balances = balanceRepository.findByFileId(file);
    return balanceMapper.convertToDto(balances);
  }

  /**
   * gets turnovers by file name
   * @param name name of the file to find turnovers by
   * @return list containing all turnovers
   */
  public List<TurnoverDto> getTurnovers(String name) {
    File file = fileRepository.findByName(name);
    List<Turnover> turnovers = turnoverRepository.findByFileId(file);
    return turnoverMapper.convertToDto(turnovers);
  }

  /**
   * imports content from excel file to database
   *
   * @param filePath path of the file to import
   */
  public void importToDatabase(Path filePath) {
    try {
      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath.toString()));
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      HSSFSheet sheet = wb.getSheetAt(0);
      excelUtils.initHeaders(sheet);

      currentFile = new File(filePath.getFileName().toString());
      fileRepository.create(currentFile);

      LocalDate[] dates = excelUtils.getTimeRange(sheet);
      from = dates[0];
      to = dates[1];

      for (int rowNum = excelUtils.getDataStartRow(sheet);
          rowNum < sheet.getLastRowNum();
          rowNum++) {
        Row row = sheet.getRow(rowNum);
        if (Objects.nonNull(row)) {
          Cell firstCell = row.getCell(0);
          if (excelUtils.isSumRow(firstCell)) continue;
          if (excelUtils.isClassRow(firstCell)) {
            bankClass = Converter.stringToClassObject(firstCell.getStringCellValue());
            classRepository.create(bankClass);
            continue;
          }
          parseExcelToObjects(row);
          saveToDatabase(openBalance, closeBalance, turnover);
        }
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * extracts data from cells and creates entities using it
   *
   * @param row row to extract data from
   */
  private void parseExcelToObjects(Row row) {
    Cell billCell = row.getCell(Headers.BILL.getColumnIdx());
    Cell openBalanceAssetCell = row.getCell(Headers.OPENING_BALANCE_ASSETS.getColumnIdx());
    Cell openBalanceLiabilityCell = row.getCell(Headers.OPENING_BALANCE_LIABILITY.getColumnIdx());
    Cell turnOverDebitCell = row.getCell(Headers.TURNOVER_DEBIT.getColumnIdx());
    Cell turnOverCreditCell = row.getCell(Headers.TURNOVER_CREDIT.getColumnIdx());
    Cell closeBalanceAssetCell = row.getCell(Headers.CLOSING_BALANCE_ASSETS.getColumnIdx());
    Cell closeBalanceLiabilityCell = row.getCell(Headers.CLOSING_BALANCE_LIABILITY.getColumnIdx());

    openBalance =
        new Balance(
            bankClass,
            BalanceType.OPENING,
            Integer.parseInt(billCell.getStringCellValue()),
            BigDecimal.valueOf(openBalanceAssetCell.getNumericCellValue()),
            BigDecimal.valueOf(openBalanceLiabilityCell.getNumericCellValue()),
            from,
            to,
            currentFile);
    turnover =
        new Turnover(
            bankClass,
            Integer.parseInt(billCell.getStringCellValue()),
            BigDecimal.valueOf(turnOverDebitCell.getNumericCellValue()),
            BigDecimal.valueOf(turnOverCreditCell.getNumericCellValue()),
            from,
            to,
            currentFile);
    closeBalance =
        new Balance(
            bankClass,
            BalanceType.CLOSING,
            Integer.parseInt(billCell.getStringCellValue()),
            BigDecimal.valueOf(closeBalanceAssetCell.getNumericCellValue()),
            BigDecimal.valueOf(closeBalanceLiabilityCell.getNumericCellValue()),
            from,
            to,
            currentFile);
  }

  /**
   * saves objects to database
   *
   * @param openBalance opening balance object to save
   * @param closeBalance closing balance object to save
   * @param turnover turnover object to save
   */
  private void saveToDatabase(Balance openBalance, Balance closeBalance, Turnover turnover) {
    balanceRepository.create(openBalance);
    balanceRepository.create(closeBalance);
    turnoverRepository.create(turnover);
  }
}
