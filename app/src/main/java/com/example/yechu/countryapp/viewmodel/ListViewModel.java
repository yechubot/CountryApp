package com.example.yechu.countryapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yechu.countryapp.di.DaggerApiComponent;
import com.example.yechu.countryapp.model.CountriesService;
import com.example.yechu.countryapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    // live data -> objects that generate value
    // live data is observable
    //values are generated async
    // 누가 value 받는지는 뷰모델이 모름
    //attach만 되어 있다면 그냥 generate value

    // live data is a type of observable
    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public CountriesService countriesService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(){
        super();// extends ViewModel 때문에
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {
/*
        //mock data
        CountryModel country1 = new CountryModel("Korea","seoul","null");
        CountryModel country2 = new CountryModel("USA","washington d.c","");

        List<CountryModel> list = new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        list.add(country2);
        list.add(country1);
        list.add(country2);
        countries.setValue(list);
        countryLoadError.setValue(false);
        loading.setValue(false);
        */
        loading.setValue(true);// not sure when data will be loading
        disposable.add(//메모리 손실 방지?

                // 백그라운드 스레드에서 작동 ?
                countriesService.getCountries()
                .subscribeOn(Schedulers.newThread()) // 새로운 스레드에서 response 기다릴 떄
                .observeOn(AndroidSchedulers.mainThread())// response 오면 메인 스레드로
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>(){

                    @Override
                    public void onSuccess(List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {//앱이 종료될 떄 호출됨
        super.onCleared();
        disposable.clear();
    }
}
