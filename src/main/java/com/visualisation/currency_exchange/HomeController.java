package com.visualisation.currency_exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;


@Controller
public class HomeController {

    private CurrencyData currencyData = new CurrencyData();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model model) {
        model.addAttribute("currencyData", currencyData);
        ResponseEntity<TreeMap<String, String>> response = restTemplate.exchange(
                "https://openexchangerates.org/api/currencies.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TreeMap<String, String>>() {
                });
        currencyData.setFrom(response.getBody());
        currencyData.setTo(response.getBody());
        return "index";
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
