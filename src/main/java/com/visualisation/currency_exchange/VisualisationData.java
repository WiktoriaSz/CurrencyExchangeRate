package com.visualisation.currency_exchange;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class VisualisationData {

    public VisualisationData() {
    }

    // interval: 1 hour
    // only today
    public String getJsonCallForHourlyChanges(CurrencyData currencyData) {
        return "https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&interval=60min&apikey=VN0IF14PA3IA698G";
    }

//    https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol=EUR&to_symbol=USD&interval=60min&apikey=VN0IF14PA3IA698G

    // interval: 1 day
    // only this month
    public String getJsonCallForDailyChanges(CurrencyData currencyData) {
        return "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
    }

    // interval 1 week
    // only this year
    public String getJsonCallForWeeklyChanges(CurrencyData currencyData) {
        return "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
    }

    // interval 1 month
    // only a year
    public String getJsonCallForMontlhyChanges(CurrencyData currencyData) {
        return "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
    }

//    public List<VisualisationObject> getJsonToListOfMaps(String json){
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<VisualisationObject>> response = restTemplate.exchange(
//                json,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<VisualisationObject>>() {
//                });
//        return response.getBody();
//    }
}
