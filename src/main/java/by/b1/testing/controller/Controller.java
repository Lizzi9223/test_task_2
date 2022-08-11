package by.b1.testing.controller;

import by.b1.testing.service.services.MainService;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test_task_2")
public class Controller {
  private final MainService mainService;

  @Autowired
  public Controller(MainService mainService) {
    this.mainService = mainService;
  }

  @PostMapping("/import")
  public ResponseEntity<Void> importFromFileToDatabase() {
    mainService.importToDatabase(Path.of("C:\\Users\\User\\Downloads\\ОСВ для тренинга.xls"));
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
