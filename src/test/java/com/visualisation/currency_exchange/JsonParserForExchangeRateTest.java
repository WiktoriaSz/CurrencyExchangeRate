package com.visualisation.currency_exchange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserForExchangeRateTest {

    private JsonParserForExchangeRate parser;
    private String exampeJson;

    @Before
    public void setUp() {
        parser = new JsonParserForExchangeRate();
        exampeJson = "{\n" +
                "    \"Realtime Currency Exchange Rate\": {\n" +
                "        \"1. From_Currency Code\": \"USD\",\n" +
                "        \"2. From_Currency Name\": \"United States Dollar\",\n" +
                "        \"3. To_Currency Code\": \"JPY\",\n" +
                "        \"4. To_Currency Name\": \"Japanese Yen\",\n" +
                "        \"5. Exchange Rate\": \"109.30000000\",\n" +
                "        \"6. Last Refreshed\": \"2019-05-26 09:04:10\",\n" +
                "        \"7. Time Zone\": \"UTC\",\n" +
                "        \"8. Bid Price\": \"109.28000000\",\n" +
                "        \"9. Ask Price\": \"109.31000000\"\n" +
                "    }\n" +
                "}";
    }

    @Test
    public void jsonParserTest() {
        assertEquals("109.30000000", parser.parseCurrentExchangeRateJson(exampeJson));
        String emptyString = "";
        assertNull(parser.parseCurrentExchangeRateJson(emptyString));
    }

    @Test
    public void jsonParserTestWithEmptyString() {
        String emptyString = "";
        assertNull(parser.parseCurrentExchangeRateJson(emptyString));
    }

    @Test
    public void jsonParserTestWithNoDataMessage() {
        String errorString = "No data";
        assertNull(parser.parseCurrentExchangeRateJson(errorString));
    }

    @Test
    public void jsonParserTestWithErrorMessage() {
        String errorString = "Error, please try again";
        assertNull(parser.parseCurrentExchangeRateJson(errorString));
    }

}