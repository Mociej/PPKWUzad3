package ppkwuzad3.ppkwuzad3.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ppkwuzad2.ppkwuzad2.Controllers.StringApiController;
import ppkwuzad3.ppkwuzad3.StringResultToMap;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileSaverApiController {
    @GetMapping("/savestatstofile/{filetype}/{input}")
    public String saveStatsToFile(@PathVariable String filetype, @PathVariable String input) {
        StringApiController stringApiController = new StringApiController();
        String result = "";
        StringResultToMap stringResultToMap = new StringResultToMap();
        Map<String, String> elements = stringResultToMap.stringToMap(stringApiController.stringAnalyse(input));


        switch (filetype) {
            case "txt":
                String tmp = stringApiController.stringAnalyse(input);
                String lines[] = tmp.trim().split("<br/>");

                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].length() > 0)
                        result += lines[i] + "\n";
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
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                XMLEncoder xmlEncoder = new XMLEncoder(byteArrayOutputStream);
                xmlEncoder.writeObject(elements);
                xmlEncoder.close();
                result = byteArrayOutputStream.toString();
                break;
            case "csv":
                for (Map.Entry<String, String> entry : elements.entrySet()) {
                    result += entry.getKey() + "," + entry.getValue() + "\n";
                }

                break;
            default:
                return "File type must be txt, json, xml or csv.";
        }

        return result;
    }
}
