package ppkwuzad3.ppkwuzad3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringResultToMap {
    public Map<String, String> stringToMap(String tmp){
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

            elements.put(keyvalue[0], keyvalue[1]);
        }

        return elements;
    }
}
