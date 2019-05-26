package com.visualisation.currency_exchange;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyDataTest {
    private CurrencyData currencyData;
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        currencyData = new CurrencyData();
        restTemplate = new RestTemplate();
    }

    @Test
    public void supportMethodForConstructorTest(){
        Map<String, String> map = currencyData.getAvailableCurrencyForExchange(restTemplate);
        assertTrue(map.containsKey("GBP"));
        assertEquals("Polish Zloty", map.get("PLN"));
    }
}
