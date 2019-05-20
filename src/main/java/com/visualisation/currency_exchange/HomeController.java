package com.visualisation.currency_exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Controller
public class HomeController {

    private CurrencyData currencyData = new CurrencyData();

    @Autowired
    RestTemplate restTemplate;

    // https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=JPY&apikey=VN0IF14PA3IA698G

    @GetMapping("/")
    public String showMain(Model model) {
        model.addAttribute("currencyData", currencyData);
        return "index";

        // TODO: jak zrobić, żeby nic nie wyświetlało na początku, a potem wyświetlało wyniki metod get choice
    }

    @PostMapping("/d")
    public String displayExchange(@ModelAttribute("currencyData") CurrencyData currencyData, ModelMap map) {
        String apiCall = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
                + currencyData.getChoice1() + "&to_currency="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";

        String response = restTemplate.getForObject(
                apiCall, String.class
        );

            JsonParserForExchangeRate parser = new JsonParserForExchangeRate();
        if (currencyData.getChoice1() == null && currencyData.getChoice2() == null) {
            return "error";

//            System.out.println("\n\n\n" + "choice1: " + currencyData.getChoice1() + "\n\n\n");
//        System.out.println("\n\n\n" + apiCall);
//        System.out.println("\n\n\n" + response);
//        model.addAttribute("currencyData", currencyData);


        } else {
            map.addAttribute("currencyData", currencyData);
            String rate = parser.parseCurrentExchangeRateJson(response);
            map.addAttribute("realtime", rate);
            return "index";
        }

    }


//    @GetMapping("/display")
//    public String displayRepository(Model model) {
//        model.addAttribute("accName", user.getAccountName());
//        Repository repo = user.getRecentlyPushed(
//                user.getRepositoryListFromRestTemplateGetMethod(user, restTemplate),
//                LocalDateTime.now());
//        model.addAttribute("repoName", repo.getName());
//        model.addAttribute("repoDate", ZonedDateTime.parse(repo.getPushed_at()).toLocalDateTime());
//        return "index";
//    }
//
//    default Repository getRecentlyPushed(List<Repository> list, LocalDateTime now){
//        if (!list.isEmpty()) {
//            LocalDateTime temporary = LocalDateTime.from(now);
//            long seconds = temporary.until(ZonedDateTime.parse(list.get(0).getPushed_at()), ChronoUnit.SECONDS);
//
//            Repository rep = list.get(0);
//            for (Repository repo : list) {
//                if (seconds < temporary.until(ZonedDateTime.parse(repo.getPushed_at()), ChronoUnit.SECONDS)) {
//                    rep = repo;
//                    seconds = temporary.until(ZonedDateTime.parse(repo.getPushed_at()), ChronoUnit.SECONDS);
//                }
//            }
//            return rep;
//        } else {
//            return null;
//        }
}
