package edu.galileo.android.tipcalc.model;

import java.util.Date;

/**
 * Created by ykro.
 */
public class TipRecord {
    Date timestamp;
    double bill;
    double tipPercentage;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(double tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public double getTip() {
        return bill*tipPercentage;
    }
}
