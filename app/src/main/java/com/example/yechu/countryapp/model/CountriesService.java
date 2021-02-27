package com.example.yechu.countryapp.model;

import com.example.yechu.countryapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesService {

    //singletone
    private static CountriesService instance;

    //retrofit api
    /* api - json response 다.
    * 근데 이를 Country Model로 변경하려고 함 -> gson converter factory 가 하는 일
    * 그러면 adaptor factory가 해당 리스트를 rx java 컴포넌트로 (Single)로 바꾼다  - observable
    *
    * */

    @Inject
    public CountriesApi api; // dagger2 : create / inject로 나눠서 사용할 수 있음

    private CountriesService(){
        DaggerApiComponent.create().inject(this);
    }

    public static CountriesService getInstance(){
        if(instance==null){
            instance = new CountriesService();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return api.getCountries();
    }


}
