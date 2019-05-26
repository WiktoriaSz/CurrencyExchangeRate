package com.visualisation.currency_exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonParserForVisualisation {

    public JsonParserForVisualisation() {
    }

    public String getJsonStringWithoutRoot(String json) {
        int i = json.indexOf(")\":");
        return json.substring(i + 4, json.length() - 2);
    }

    public List<VisualisationObject> getVisualisationObjectFromJsonString(String json, ObjectMapper mapper)
            throws IOException {
        if(json == null){
            return null;
        } else {
            String jsonString = getJsonStringWithoutRoot(json);
            Map<String, LinkedHashMap<String, String>> map = mapper.readValue(
                    jsonString, new TypeReference<Map<String, Object>>() {
                    });
            List<VisualisationObject> list = new ArrayList<>();
            map.forEach((key, value) -> {
                VisualisationObject v = new VisualisationObject();
                try {
                    v.setDate(dateFormatter(key));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                v.setOpen(Double.parseDouble(value.get("1. open")));
                v.setHigh(Double.parseDouble(value.get("2. high")));
                v.setLow(Double.parseDouble(value.get("3. low")));
                v.setClose(Double.parseDouble(value.get("4. close")));
                list.add(v);
            });
            return list;
        }
    }

    private Date dateFormatter(String date) throws ParseException {
        SimpleDateFormat formatter;
        if (date.contains(" ")) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        return formatter.parse(date);
    }
}
