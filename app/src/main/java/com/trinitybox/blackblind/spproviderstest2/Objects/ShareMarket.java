package com.trinitybox.blackblind.spproviderstest2.Objects;

public class ShareMarket {

    String quote;
    String dateValue;
    String timeValue;

    public ShareMarket() {
    }

    public ShareMarket(String quote, String dateValue, String timeValue) {
        this.quote = quote;
        this.dateValue = dateValue;
        this.timeValue = timeValue;
    }

    public String getQuote() {

        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }
}
