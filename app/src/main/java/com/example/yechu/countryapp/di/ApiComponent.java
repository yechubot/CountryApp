package com.example.yechu.countryapp.di;

import com.example.yechu.countryapp.model.CountriesService;
import com.example.yechu.countryapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    //모듈과 인젝션 위치랑 연결해줌

    void inject(CountriesService service); // where it needs to be inject

    void inject(ListViewModel viewModel);
}
