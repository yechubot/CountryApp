package com.example.yechu.countryapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {
    // retrofit !!

    @GET("DevTides/countries/master/countriesV2.json") //""안에 api endpoint 사용
    //Single - type of Observable ( by rx java )
    Single<List<CountryModel>> getCountries();

    //여기서 중요한건 back end api 가 제공하는 것은 country model List 지,
    // country model을 제공하는 것이 아니다. 그렇기 때문에 type이 <List<CountryModel>>이
    //되어야 한다.

   /* //링크가 없다면 다음과 같이 넣어서 할 수도 있음
    @GET
    Single<Object> getObject(@Url String urlString);

    @POST("endpoint2")
    Single<Object> getFromEndPoint2();*/

}
