package com.visualisation.currency_exchange;

public class JsonParserForExchangeRate {

    public JsonParserForExchangeRate() {
    }

    public String parseCurrentExchangeRateJson(String json){
        String [] temp1 = json.split(",");
        String[] temp2 = temp1[4].trim().split(" ");
        String temp3 = temp2[3].substring(1, temp2[3].length()-1);
        return temp3;
    }

}
