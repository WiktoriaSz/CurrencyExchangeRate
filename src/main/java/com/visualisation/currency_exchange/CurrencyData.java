package com.visualisation.currency_exchange;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyData {
    private Map<String, String> pool = new HashMap<>();
    private String choice1;
    private String choice2;
    private String realtime;


    //***********************************constructors**********************************************

    public CurrencyData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TreeMap<String, String>> response = restTemplate.exchange(
                "https://openexchangerates.org/api/currencies.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TreeMap<String, String>>() {
                });
        setPool(response.getBody());
    }


    //***********************************getters & setters*****************************************


    public Map<String, String> getPool() {
        return pool;
    }

    private void setPool(Map<String, String> pool) {
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

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }
}
