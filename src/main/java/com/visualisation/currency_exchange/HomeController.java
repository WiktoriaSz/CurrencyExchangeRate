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
    private String errorMessage = "Number for data access call for today was reached. " +
            "Alternatively frequency of data access was surpassed. Please try again in a few seconds.";

    @Autowired
    ChartVisualisation visualisationData;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model map) {
        map.addAttribute("currencyData", currencyData);
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
            currencyData.setRealtime(parseRate.parseCurrentExchangeRateJson(response));
            map.addAttribute("realtime", currencyData.getRealtime());
            return displayChart(currencyData, map);
        }
    }

    @PostMapping(params = "exchange", value = "/")
    public String exchangeChoices(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        currencyData.exchangeChoices();
        map.addAttribute("currencyData", currencyData);
        return displayExchange(currencyData, map);
    }

    @SuppressWarnings("Duplicates")
    @PostMapping(params = "chart", value = "/")
    private String displayChart(@ModelAttribute CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", currencyData.getRealtime());
        String call = visualisationData.getJsonCallForHourlyChanges(currencyData);
        String jsonString = restTemplate.getForObject(call, String.class);
        if (jsonString.contains("Error Message")) {
            map.addAttribute("errorMessage", "No historical data available to create a chart for this currency pair: " +
                    currencyData.getPool().get(currencyData.getChoice1()) + ", " +
                    currencyData.getPool().get(currencyData.getChoice2()));
        } else if (jsonString.contains("Note")) {
            map.addAttribute("errorMessage", errorMessage);
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

    @PostMapping(params = "hour", value = "/")
    private String displayHourlyChart(@ModelAttribute CurrencyData currencyData, ModelMap map) {
        return displayChart(currencyData, map);
    }

    @SuppressWarnings("Duplicates")
    @PostMapping(params = "day", value = "/")
    private String displayDailyChart(@ModelAttribute CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", currencyData.getRealtime());
        String call = visualisationData.getJsonCallForDailyChanges(currencyData);
        String jsonString = restTemplate.getForObject(call, String.class);
        if (jsonString.contains("Note")) {
            map.addAttribute("errorMessage", errorMessage);
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

    @SuppressWarnings("Duplicates")
    @PostMapping(params = "week", value = "/")
    private String displayWeeklyChart(@ModelAttribute CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", currencyData.getRealtime());
        String call = visualisationData.getJsonCallForWeeklyChanges(currencyData);
        String jsonString = restTemplate.getForObject(call, String.class);
        if (jsonString.contains("Note")) {
            map.addAttribute("errorMessage", errorMessage);
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

    @SuppressWarnings("Duplicates")
    @PostMapping(params = "month", value = "/")
    private String displayMonthlyChart(@ModelAttribute CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", currencyData.getRealtime());
        String call = visualisationData.getJsonCallForMonthlyChanges(currencyData);
        String jsonString = restTemplate.getForObject(call, String.class);
        if (jsonString.contains("Note")) {
            map.addAttribute("errorMessage", errorMessage);
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
