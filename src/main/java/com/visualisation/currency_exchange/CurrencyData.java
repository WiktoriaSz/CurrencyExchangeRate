package com.visualisation.currency_exchange;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyData {
    private Map<String, String> pool = new HashMap<>();
    private String choice1;
    private String choice2;
    private String realtime;


    public void exchangeChoices() {
        String temp1 = new String(choice1);
        String temp2 = new String(choice2);
        setChoice1(temp2);
        setChoice2(temp1);
    }

    public String getStringForApiCallForCurrencyExchangeData() {
        String call = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
                + getChoice1() + "&to_currency="
                + getChoice2() + "&apikey=VN0IF14PA3IA698G";
        return call;
    }

    public TreeMap<String, String> getAvailableCurrencyForExchange(RestTemplate restTemplate) {
        ResponseEntity<TreeMap<String, String>> response = restTemplate.exchange(
                "https://openexchangerates.org/api/currencies.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TreeMap<String, String>>() {
                });
        return response.getBody();
    }

    //***********************************constructors**********************************************

    public CurrencyData() {
        RestTemplate restTemplate = new RestTemplate();
        setPool(getAvailableCurrencyForExchange(restTemplate));
        setRealtime(" ");
    }


    //***********************************getters & setters*****************************************


    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public Map<String, String> getPool() {
        return pool;
    }

    public void setPool(Map<String, String> pool) {
        this.pool = pool;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

}
