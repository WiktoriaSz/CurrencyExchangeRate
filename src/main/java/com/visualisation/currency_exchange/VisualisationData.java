package com.visualisation.currency_exchange;
import org.springframework.web.client.RestTemplate;

public class VisualisationData {
    private RestTemplate restTemplate = new RestTemplate();

    public VisualisationData() {
    }

    // interval: 1 hour
    public String getJsonCallForHourlyChanges(CurrencyData currencyData) {
        String call = "https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&interval=60min&apikey=VN0IF14PA3IA698G";
        return call;
//        return restTemplate.getForObject(call, String.class);

    }

//    // interval: 1 day
//    public String getJsonCallForDailyChanges(CurrencyData currencyData) {
//        String call = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="
//                + currencyData.getChoice1() + "&to_symbol="
//                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
//        return restTemplate.getForObject(call, String.class);
//    }
//
//    // interval 1 week
//    public String getJsonCallForWeeklyChanges(CurrencyData currencyData) {
//        String call = "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol="
//                + currencyData.getChoice1() + "&to_symbol="
//                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
//        return restTemplate.getForObject(call, String.class);
//    }
//
//    // interval 1 month
//    public String getJsonCallForMontlhyChanges(CurrencyData currencyData) {
//        String call = "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol="
//                + currencyData.getChoice1() + "&to_symbol="
//                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
//        return restTemplate.getForObject(call, String.class);
//    }

}
