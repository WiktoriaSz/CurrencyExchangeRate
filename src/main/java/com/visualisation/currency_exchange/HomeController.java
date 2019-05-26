package com.visualisation.currency_exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Controller
public class HomeController {

    private CurrencyData currencyData = new CurrencyData();
    private JsonParserForExchangeRate parseRate = new JsonParserForExchangeRate();
    private JsonParserForVisualisation parseVisual = new JsonParserForVisualisation();

    @Autowired
    ChartVisualisation visualisationData;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", "Select");
        map.addAttribute("choice2", "Select");
        map.addAttribute("realtime", " ");
        return "index";
    }

    @PostMapping(params = "get_rate", value = "/")
    public String displayExchange(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        String json = currencyData.getStringForApiCallForCurrencyExchangeData();
        String response = restTemplate.getForObject(json, String.class);

        if (currencyData.getChoice1().isEmpty() || currencyData.getChoice2().isEmpty()
                || response.contains("error")) {
            return "error";
        } else {
            map.addAttribute("realtime", parseRate.parseCurrentExchangeRateJson(response));
            return displayChart(currencyData, map, parseRate.parseCurrentExchangeRateJson(response));
        }
    }

    @PostMapping(params = "exchange", value = "/")
    public String exchangeChoices(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        currencyData.exchangeChoices();
        map.addAttribute("currencyData", currencyData);
        return displayExchange(currencyData, map);
    }

    @PostMapping(params = "chart", value = "/")
    private String displayChart(@ModelAttribute CurrencyData currencyData, ModelMap map,
                                String parseCurrentExchangeRateJson) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", parseCurrentExchangeRateJson);
        String call = visualisationData.getJsonCallForHourlyChanges(currencyData);
        String jsonString = restTemplate.getForObject(call, String.class);
        System.out.println(jsonString);
        System.out.println(call);
        if (jsonString.contains("Error Message")) {
            map.addAttribute("errorMessage",
                    "No historical data available for this currency pair: \n" +
                            currencyData.getPool().get(currencyData.getChoice1()) + ", " +
                            currencyData.getPool().get(currencyData.getChoice2()));
        } else if (jsonString.contains("Note")) {
            map.addAttribute("errorMessage",
                    "Number for data access call for today was reached. " +
                            "\nAlternatively frequency of data access was surpassed. Please try again in a few seconds.");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<VisualisationObject> list = parseVisual.getVisualisationObjectFromJsonString(jsonString, mapper);
                map.addAttribute("list", list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
