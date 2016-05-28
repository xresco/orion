package com.abed.orion.controller;


import com.abed.orion.BuildConfig;
import com.abed.orion.model.User;
import com.abed.orion.model.eventBuses.UsersLoadedEvent;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Utility class where all the API calls can be place
 * It makes it easier when any amendments on the APIs are needed
 */

public class API_Utils {
    private String TAG = getClass().getName();

    /**
     * Call the api that loads the users and triggers a bus event to notifiy the UI
     */
    public static void loadUsers() {
        new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()
                .create(UsersService.class)
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            loadUsers();
                        }
                    }

                    @Override
                    public void onNext(List<User> users) {
                        RxBus.getInstance().postOnTheUiThread(new UsersLoadedEvent(users));
                    }
                });
    }


}