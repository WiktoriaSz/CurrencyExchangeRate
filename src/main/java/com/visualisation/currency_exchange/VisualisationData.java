package com.visualisation.currency_exchange;

import org.springframework.stereotype.Component;

@Component
public class VisualisationData implements ChartVisualisation{

    public VisualisationData() {
    }

    @Override
    public String getJsonCallForHourlyChanges(CurrencyData currencyData) {
        String call = "https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&interval=60min&apikey=VN0IF14PA3IA698G";
        return call;
    }

    @Override
    public String getJsonCallForDailyChanges(CurrencyData currencyData) {
        String call = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
        return call;
    }

    @Override
    public String getJsonCallForWeeklyChanges(CurrencyData currencyData) {
        String call = "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
        return call;
    }

    @Override
    public String getJsonCallForMonthlyChanges(CurrencyData currencyData) {
        String call = "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol="
                + currencyData.getChoice1() + "&to_symbol="
                + currencyData.getChoice2() + "&apikey=VN0IF14PA3IA698G";
        return call;
    }

}
