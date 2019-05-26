package com.visualisation.currency_exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonParserForVisualisationTest {

    private JsonParserForVisualisation parser;
    private ObjectMapper mapper;
    private String exampleString;

    @Before
    public void setUp() {
        parser = new JsonParserForVisualisation();
        mapper = new ObjectMapper();
        exampleString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"FX Intraday (60min) Time Series\",\n" +
                "        \"2. From Symbol\": \"GBP\",\n" +
                "        \"3. To Symbol\": \"PLN\",\n" +
                "        \"4. Last Refreshed\": \"2019-05-24 22:00:00\",\n" +
                "        \"5. Interval\": \"60min\",\n" +
                "        \"6. Output Size\": \"Compact\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (60min)\": {\n" +
                "        \"2019-05-24 22:00:00\": {\n" +
                "            \"1. open\": \"4.8687\",\n" +
                "            \"2. high\": \"4.8687\",\n" +
                "            \"3. low\": \"4.8687\",\n" +
                "            \"4. close\": \"4.8687\"\n" +
                "        },\n" +
                "        \"2019-05-24 21:00:00\": {\n" +
                "            \"1. open\": \"4.8690\",\n" +
                "            \"2. high\": \"4.8720\",\n" +
                "            \"3. low\": \"4.8680\",\n" +
                "            \"4. close\": \"4.8680\"\n" +
                "        },\n" +
                "        \"2019-05-24 20:00:00\": {\n" +
                "            \"1. open\": \"4.8684\",\n" +
                "            \"2. high\": \"4.8710\",\n" +
                "            \"3. low\": \"4.8573\",\n" +
                "            \"4. close\": \"4.8573\"\n" +
                "        },\n" +
                "        \"2019-05-24 19:00:00\": {\n" +
                "            \"1. open\": \"4.8693\",\n" +
                "            \"2. high\": \"4.8707\",\n" +
                "            \"3. low\": \"4.8658\",\n" +
                "            \"4. close\": \"4.8666\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    @Test
    public void jsonWithoutRootTest() {
        assertTrue(parser.getJsonStringWithoutRoot(exampleString).startsWith("{\n" +
                "        \"2019-05-24 22:00:00\": {\n"));
        assertFalse(parser.getJsonStringWithoutRoot(exampleString).contains("\"Meta Data\":"));
    }

    @Test
    public void getVisualisationObjectListTest() throws IOException {
        List<VisualisationObject> objects = parser.getVisualisationObjectFromJsonString(exampleString, mapper);
        assertEquals(4, objects.size());
        assertSame(objects.get(2).getDate().getClass(), Date.class);
        assertSame(objects.get(2).getHigh().getClass(), Double.class);
        assertEquals("4.8666", (objects.get(3).getClose().toString()));
        assertEquals("4.8687", (objects.get(0).getClose().toString()));
        assertEquals("4.8573", (objects.get(2).getLow().toString()));
    }

    @Test
    public void visualisationObjectListTest() throws IOException {
        List<VisualisationObject> objects = parser.getVisualisationObjectFromJsonString(null, mapper);
        assertNull(objects);
    }
}
