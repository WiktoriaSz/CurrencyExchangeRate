package com.visualisation.currency_exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class HomeController {

    private CurrencyData currencyData = new CurrencyData();
    private JsonParserForExchangeRate parseRate = new JsonParserForExchangeRate();
    private JsonParserForVisualisation parseVisual = new JsonParserForVisualisation();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("realtime", " ");
//        List<VisualisationObject> list = new ArrayList<>();                                  // 3.
//        map.addAttribute("list", list);
        return "index";
    }
    // TODO: poprawić css - układ, buttons, realtime box (error page ok)
    // TODO: wykres - zobaczyć dane - utworzyć klasy? - wykres google
    // TODO: usunąć niepotrzebne klasy, komentarze, importy, interfejsy, dodatkowe foldery?
    // TODO: unit testy + resttemplate
    // TODO: heroku


    @PostMapping(params = "get_rate", value = "/")
    public String displayExchange(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        map.addAttribute("currencyData", currencyData);

        String apiCall = currencyData.getStringForApiCallForCurrencyExchangeData();
        String response = restTemplate.getForObject(apiCall, String.class);
//        if (response == null) {
        if (currencyData.getChoice1().isEmpty() || currencyData.getChoice2().isEmpty()
                || response.contains("error")) {
            return "error";
        } else {
            map.addAttribute("realtime", parseRate.parseCurrentExchangeRateJson(response));
            return displayChart(currencyData, map, parseRate.parseCurrentExchangeRateJson(response));   // 1.
//            return "index";
        }
    }

    @PostMapping(params = "exchange", value = "/")
    public String exchangeChoices(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        currencyData.exchangeChoices();
        map.addAttribute("currencyData", currencyData);
        return displayExchange(currencyData, map);
    }

    @PostMapping(params = "chart", value = "/")                                               // 2.
    private String displayChart(@ModelAttribute CurrencyData currencyData, ModelMap map,
                                String parseCurrentExchangeRateJson) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("realtime", parseCurrentExchangeRateJson);
        System.out.println("**********************************************++++++++++++++++++++++++++++++");
        VisualisationData visualisationData = new VisualisationData();
        String todaysData = visualisationData.getJsonCallForHourlyChanges(currencyData);
        System.out.println(todaysData);
        String jsonString = restTemplate.getForObject(todaysData, String.class);
//                System.out.println(jsonString);
        if(jsonString!=null && jsonString.contains("Error Message")) {
            map.addAttribute("errorMessage", "No data available for this currency pair.");
        } else if (jsonString!=null && jsonString.contains("Note")){
            map.addAttribute("errorMessage", "Number for data access call for today was reached." +
                    "Alternatively frequency of data access was surpassed. Please try again in few seconds.");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<VisualisationObject> list = parseVisual.getVisualisationObjectFromJsonString(jsonString, mapper);
                System.out.println(list.get(0));
//                String list = parseVisual.getJsonStringWithoutRoot(jsonString);
                map.addAttribute("list", list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }


}
