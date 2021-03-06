package com.example.sun.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
/**
 * Created by sun on 16/8/19.
 *
 * 出自：http://www.cnblogs.com/Fndroid/p/5762523.html
 *
 * 相关：http://gank.io/post/560e15be2dca930e00da1083
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        //分式结构
        setRxjava();
        //链式结构
        setRxjava1();
        //只关心onNext();
        setRxjava2();
        //只关心onNext()和onError();
        setRxjava3();
    }

    private void setupView() {
        mTextView1 = (TextView) findViewById(R.id.text_view1);
        mTextView1.setOnClickListener(this);
        mTextView2 = (TextView) findViewById(R.id.text_view2);
        mTextView2.setOnClickListener(this);
        mTextView3 = (TextView) findViewById(R.id.text_view3);
        mTextView3.setOnClickListener(this);
        mTextView4 = (TextView) findViewById(R.id.text_view4);
        mTextView4.setOnClickListener(this);
    }


    private void setRxjava() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("word");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "分式结构---onCompleted()方法被调用");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "分式结构---onError()方法被调用");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "分式结构---onNext()方法被调用,s=" + s);
            }
        };
        observable.subscribe(subscriber);
    }

    private void setRxjava1() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "链式结构—--onCompleted()方法被调用");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "链式结构—--onError()方法被调用");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "链式结构—--onNext()方法被调用，integer=" + integer);
            }
        });

    }

    private void setRxjava2() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "只关心onNext方法—--onNext()方法被调用，s=" + s);
            }
        });


    }

    private void setRxjava3() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "只关心onNext()和onerror()方法—--onNext()方法被调用，s=" + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "只关心onNext()和onerror()方法—--onError()方法被调用");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_view1:
                Intent intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view2:
                Intent intent2 = new Intent(MainActivity.this,ChangeActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_view3:
                Intent intent3 = new Intent(MainActivity.this,FilterActivity.class);
                startActivity(intent3);
                break;
            case R.id.text_view4:
                Intent intent4 = new Intent(MainActivity.this,ThreadActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
