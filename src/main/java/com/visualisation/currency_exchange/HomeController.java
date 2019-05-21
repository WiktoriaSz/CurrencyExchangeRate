package com.visualisation.currency_exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    private CurrencyData currencyData = new CurrencyData();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model model) {
        model.addAttribute("currencyData", currencyData);
        return "index";

        // TODO: usunąć formFunctions.js jeśli nie potrzebny
        // TODO: poprawić css
        // TODO: wykres - zobaczyć dane - utworzyć klasy? - wykres google
        // TODO: testy
        // TODO: heroku
    }

    @PostMapping(params = "get_rate", value = "/")
    public String displayExchange(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        String apiCall = currencyData.getStringForApiCallForCurrencyExchangeData();
        String response = restTemplate.getForObject(
                apiCall, String.class
        );

        if (currencyData.getChoice1() == null && currencyData.getChoice2() == null
                || response == null) {
            return "error";
        } else {
            JsonParserForExchangeRate parser = new JsonParserForExchangeRate();
            map.addAttribute("currencyData", currencyData);
            map.addAttribute("realtime", parser.parseCurrentExchangeRateJson(response));
            return "index";
        }
    }

    @PostMapping(params = "exchange", value = "/")
    public String exchangeChoices(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        currencyData.exchangeChoices();
        map.addAttribute("currencyData", currencyData);
        return displayExchange(currencyData, map);
    }

}
