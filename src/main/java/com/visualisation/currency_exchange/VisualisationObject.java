package com.visualisation.currency_exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VisualisationObject {
    Date date;
    Double open;
    Double high;
    Double low;
    Double close;

    public VisualisationObject() {
    }

    public VisualisationObject(Date date, Double open, Double high, Double low, Double close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

//    @Override
//    public String toString() {
//        return "{\"VisualisationObject\":{"
//                + "\"date\":" + date
//                + ", \"open\":\"" + open + "\""
//                + ", \"high\":\"" + high + "\""
//                + ", \"low\":\"" + low + "\""
//                + ", \"close\":\"" + close + "\""
//                + "}}";
//    }


    @Override
    public String toString() {
        return '{' +
                "date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
}
