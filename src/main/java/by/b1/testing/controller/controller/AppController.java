package by.b1.testing.controller.controller;

import by.b1.testing.controller.consts.AttributesNames;
import by.b1.testing.controller.consts.PagesNames;
import by.b1.testing.service.services.DataService;
import by.b1.testing.service.services.FileService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Processes http requests
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/test_task_2")
public class AppController {
  private final DataService dataService;
  private final FileService fileService;

  @Autowired
  public AppController(DataService dataService, FileService fileService) {
    this.dataService = dataService;
    this.fileService = fileService;
  }

  @GetMapping("/")
  public String home(Model model) {
    return PagesNames.HOME;
  }

  @PostMapping("/import")
  private String importToDatabase(Model model, @RequestParam("file") MultipartFile file) {
    if (Objects.nonNull(file) && !file.isEmpty()) {
      try {
        byte[] fileInBytes = file.getBytes();

        String name = file.getOriginalFilename();

        String fileExtensions = ".xls";
        int lastIndex = Objects.requireNonNull(name).lastIndexOf('.');
        String substring = name.substring(lastIndex);
        if (!fileExtensions.contains(substring.toLowerCase())) return "wrong extension";

        File dir = new File("uploaded");
        if (!dir.exists()) dir.mkdirs();

        File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(fileInBytes);
        stream.flush();
        stream.close();

        dataService.importToDatabase(Path.of(uploadedFile.getAbsolutePath()));

        return getAllData(model, name);

      } catch (IOException e) {
        System.out.println(e.getMessage());
        return PagesNames.ERROR;
      }
    }
    model.addAttribute(AttributesNames.EMPTY_FILE, true);
    return PagesNames.ERROR;
  }

  @GetMapping("/files")
  private String getFiles(Model model){
    model.addAttribute(AttributesNames.FILES, fileService.getImportedFiles());
    return PagesNames.FILES;
  }

  @GetMapping("/data")
  private String getAllData(Model model, @RequestParam String fileName){
    model.addAttribute(AttributesNames.FILE_NAME, fileName);
    model.addAttribute(AttributesNames.BALANCES, dataService.getBalances(fileName));
    model.addAttribute(AttributesNames.TURNOVERS, dataService.getTurnovers(fileName));
    return PagesNames.DATA;
  }
}
