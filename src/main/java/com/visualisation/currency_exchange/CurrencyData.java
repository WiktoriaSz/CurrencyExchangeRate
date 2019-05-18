package com.visualisation.currency_exchange;

import java.util.HashMap;
import java.util.Map;

public class CurrencyData {
    private Map<String, String> from = new HashMap<>();
    private Map<String, String> to = new HashMap<>();
    private String choice1;
    private String choice2;


    //***********************************constructors**********************************************

    public CurrencyData() {
    }


    //***********************************getters & setters*****************************************

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public Map<String, String> getFrom() {
        return from;
    }

    public void setFrom(Map<String, String> from) {
        this.from = from;
    }

    public Map<String, String> getTo() {
        return to;
    }

    public void setTo(Map<String, String> to) {
        this.to = to;
    }
}
