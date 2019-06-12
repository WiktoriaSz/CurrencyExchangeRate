package com.visualisation.currency_exchange;

public interface ChartVisualisation {
    String getJsonCallForHourlyChanges(CurrencyData currencyData);
    String getJsonCallForDailyChanges(CurrencyData currencyData);
    String getJsonCallForWeeklyChanges(CurrencyData currencyData);
    String getJsonCallForMonthlyChanges(CurrencyData currencyData);
}
