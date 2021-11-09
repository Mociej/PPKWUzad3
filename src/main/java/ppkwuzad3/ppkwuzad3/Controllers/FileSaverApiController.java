package ppkwuzad3.ppkwuzad3.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ppkwuzad2.ppkwuzad2.Controllers.StringApiController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileSaverApiController {
    @GetMapping("/{filetype}/{input}")
    public String saveStatsToFile(@PathVariable String filetype, @PathVariable String input) {
        StringApiController stringApiController = new StringApiController();
        String result = "";
        String tmp = stringApiController.stringAnalyse(input);
        tmp = tmp.replaceAll("\\s", "");
        String lines[] = tmp.trim().split("<br/>");
        ArrayList<String> data = new ArrayList<String>();

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 0)
                data.add(lines[i]);
        }

        Map<String, String> elements = new HashMap();

        for (int i = 0; i < data.size(); i++) {
            String keyvalue[] = data.get(i).trim().split(":");
//            for (int j = 0; j < keyvalue.length; j++) {
//                System.out.println(keyvalue[j]);
//            }
            elements.put(keyvalue[0], keyvalue[1]);
        }
        System.out.println("+++++++++++++++++");
        switch (filetype) {
            case "txt":
                for (int i = 0; i < data.size(); i++) {
                    result += data.get(i) + "\n";
                }
                break;
            case "json":
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String json = objectMapper.writeValueAsString(elements);
                    result = json;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
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

        System.out.println(result);
        return result;
    }
}
