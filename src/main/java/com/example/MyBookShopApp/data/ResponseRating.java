package com.example.MyBookShopApp.data;


public class ResponseRating {

    private boolean result;

    private int numberAverage;

    private Double  averageValue;


    public ResponseRating() {

    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getNumberAverage() {
        return numberAverage;
    }

    public void setNumberAverage(int numberAverage) {
        this.numberAverage = numberAverage;
    }

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }
}
