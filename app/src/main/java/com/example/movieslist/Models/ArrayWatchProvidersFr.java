package com.example.movieslist.Models;

import java.util.List;

public class ArrayWatchProvidersFr
{
    List<RentArray> rent;
    List<BuyArray> buy;
    List<StreamingArray> flatrate;

    public List<RentArray> getRent() {
        return rent;
    }

    public void setRent(List<RentArray> rent) {
        this.rent = rent;
    }

    public List<BuyArray> getBuy() {
        return buy;
    }

    public void setBuy(List<BuyArray> buy) {
        this.buy = buy;
    }

    public List<StreamingArray> getFlatrate() {
        return flatrate;
    }

    public void setFlatrate(List<StreamingArray> flatrate) {
        this.flatrate = flatrate;
    }
}
