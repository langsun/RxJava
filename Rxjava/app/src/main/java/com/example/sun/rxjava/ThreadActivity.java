package com.example.sun.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sun on 16/8/19.
 */
public class ThreadActivity extends AppCompatActivity {
    private TextView mTextview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mTextview = (TextView) findViewById(R.id.text);

        setRxjava();
    }

    private void setRxjava() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
               subscriber.onNext("hello");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mTextview.setText("默认是text，现在是"+s);
            }
        });
    }
}
