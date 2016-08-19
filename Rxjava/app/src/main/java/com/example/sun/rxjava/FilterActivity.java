package com.example.sun.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by sun on 16/8/19.
 */
public class FilterActivity extends AppCompatActivity{
    private final String TAG = "FilterActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        useTake();
        useDebounce();
    }

    private void useTake() {
        Observable.interval(1, TimeUnit.SECONDS).take(3).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e(TAG,"useTake----take(3)-----along:"+aLong);
            }
        });
    }
    private void useDebounce() {
        Observable.interval(1,TimeUnit.SECONDS).debounce(2,TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e(TAG,"useDebounce---------along:"+aLong);
            }
        });
    }
}
