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
    private VisualisationData visualisationData = new VisualisationData();


    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model map) {
        map.addAttribute("currencyData", currencyData);
        map.addAttribute("choice1", " ");
        map.addAttribute("choice2", " ");
        map.addAttribute("realtime", " ");
        return "index";
    }
    // TODO: przerzucić realtime do pola)
    // TODO: poprawić css - układ, buttons, realtime box (error page ok)
    // TODO: wykres - zobaczyć dane - utworzyć klasy? - wykres google
    // TODO: usunąć niepotrzebne klasy, komentarze, importy, interfejsy, dodatkowe foldery?
    // TODO: unit testy + resttemplate
    // TODO: heroku


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
        map.addAttribute("choice1", currencyData.getPool().get(currencyData.getChoice1()));
        map.addAttribute("choice2", currencyData.getPool().get(currencyData.getChoice2()));
        map.addAttribute("realtime", parseCurrentExchangeRateJson);
        String call = visualisationData.getJsonCallForHourlyChanges(currencyData);
        System.out.println(call);
        String  jsonString = restTemplate.getForObject(call, String.class);
        if (jsonString != null && jsonString.contains("Error Message")) {
            map.addAttribute("errorMessage", "No data available for this currency pair.");
        } else if (jsonString != null && jsonString.contains("Note")) {
            map.addAttribute("errorMessage", "Number for data access call for today was reached." +
                    "Alternatively frequency of data access was surpassed. Please try again in few seconds.");
        } else {
//            return showChartByHour(currencyData, parseCurrentExchangeRateJson, map);
            // 7
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<VisualisationObject> list = parseVisual.getVisualisationObjectFromJsonString(jsonString, mapper);
                System.out.println(list.get(0));
                map.addAttribute("list", list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }

//    @PostMapping(params = "hour", value = "/")                   // 8 - powtarzany - możliwe, że metoda hour będzie zwracała główną
//    public String showChartByHour(@ModelAttribute("currencyData") CurrencyData currencyData,
//                                  String realtime, ModelMap map){
//        map.addAttribute("currencyData", currencyData);
//        map.addAttribute("realtime", realtime);
//        String jsonString = visualisationData.getJsonCallForHourlyChanges(currencyData);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            List<VisualisationObject> list = parseVisual.getVisualisationObjectFromJsonString(jsonString, mapper);
//            System.out.println(list.get(0));
//            map.addAttribute("list", list);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }

}
