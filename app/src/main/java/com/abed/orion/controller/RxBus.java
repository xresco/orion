package com.abed.orion.controller;

import android.os.Handler;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * The bus to be used to pass events from API calls and UI.
 * I'm using bus design pattern because it adds more abstraction between the UI and the logic
 * it also helps in reducing the amount of memory leaks (Leaking contexts or activities)
 */
public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    private final Subject<Object, Object> mBusSubject = new SerializedSubject<>(PublishSubject.create());
    private final Handler handler = new Handler();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public <T> Subscription register(final Class<T> eventClass, Action1<T> onNext) {
        return mBusSubject
                .filter(event -> event.getClass().equals(eventClass))
                .map(obj -> (T) obj)
                .subscribe(onNext);
    }

    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    public void postOnTheUiThread(Object event) {
        try {
            handler.postDelayed(() -> mBusSubject.onNext(event), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}