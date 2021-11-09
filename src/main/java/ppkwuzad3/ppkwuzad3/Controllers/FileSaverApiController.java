package ppkwuzad3.ppkwuzad3.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ppkwuzad2.ppkwuzad2.Controllers.StringApiController;

@RestController
public class FileSaverApiController {
    @GetMapping("/{filetype}/{input}")
    public String saveStatsToFile(@PathVariable String filetype, @PathVariable String input) {
        StringApiController stringApiController = new StringApiController();
        return stringApiController.stringAnalyse(input) + filetype;
    }
}
