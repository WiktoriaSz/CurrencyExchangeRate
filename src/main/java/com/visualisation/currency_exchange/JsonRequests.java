package com.visualisation.currency_exchange;

import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;

public interface JsonRequests {
    public String getStringForApiCallForCurrencyExchangeData();
    public TreeMap<String, String> getAvailableCurrencyForExchange(RestTemplate restTemplate);
}
