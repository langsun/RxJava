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
public class CreateActivity extends AppCompatActivity {
    private final String TAG = "CreateActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        useJust();
        useInterval();
        useFrom();
    }

    private void useJust() {
        Observable.just("hello").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "just的String类型---s:" + s);
            }
        });

        Observable.just(1, 2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e(TAG, "just的Integer类型---integer:" + integer);
            }
        });
    }

    private void useInterval() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e(TAG, "Interval的Long类型---aLong:" + aLong);
            }
        });

        Observable.interval(1,TimeUnit.SECONDS).take(3).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e(TAG, "Interval的Long类型添加了take()方法---aLong:" + aLong);
            }
        });
    }

    private void useFrom() {
        String arr[] = {"a","b"};
        Observable.from(arr).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "From的String类型---s:" + s);
            }
        });
    }


}
