package com.example.yechu.countryapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    //@를 이용해서 back end api이름이랑 매치해주기
    @SerializedName("name")
     String countryName;

    @SerializedName("capital")
     String capital;

    @SerializedName("flagPNG")
     String flag;

    public CountryModel(String countryName, String capital, String flag) {
        this.countryName = countryName;
        this.capital = capital;
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
