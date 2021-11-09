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
        String result = "";

        switch (filetype) {
            case "txt":
                result = stringApiController.stringAnalyse(input) + filetype;
                break;
            case "json":
                result = stringApiController.stringAnalyse(input) + filetype;
                break;
            case "xml":
                result = stringApiController.stringAnalyse(input) + filetype;
                break;
            case "csv":
                result = stringApiController.stringAnalyse(input) + filetype;
                break;
            default:
                return "File type must be txt, json, xml or csv.";
        }

        return result;
    }
}
