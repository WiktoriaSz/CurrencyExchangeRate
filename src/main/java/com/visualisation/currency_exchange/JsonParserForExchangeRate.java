package com.visualisation.currency_exchange;

public class JsonParserForExchangeRate {

    public JsonParserForExchangeRate() {
    }

    public String parseCurrentExchangeRateJson(String json) {
        if ((json.contains("error")) || (json.contains("Error"))) {
            return null;
        } else {
            if (json.contains(",")) {
                String[] temp1 = json.split(",");
                String[] temp2 = temp1[4].trim().split(" ");
                return temp2[3].substring(1, temp2[3].length() - 1);
            } else {
                return null;
            }
        }
    }
}
