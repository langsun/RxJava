package com.example.sun.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sun on 16/8/19.
 */
public class ChangeActivity extends AppCompatActivity {
    private final String TAG = "ChangeActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        useMap();
        Log.e(TAG, "----------------------分割线----------------------");
        unUseFlatMap();
        Log.e(TAG, "-----------------------分割线---------------------");
        useFlatMap1();
        Log.e(TAG, "----------------------分割线----------------------");
        useFlatMap1();
    }


    private void useMap() {
        Observable.interval(1, TimeUnit.SECONDS).take(5).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                return aLong.intValue();
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e(TAG, "Map---integer:" + integer);
            }
        });
    }

    private Student s1 = new Student("tom", new Course[]{new Course(98), new Course(89)});
    private Student s2 = new Student("lucy", new Course[]{new Course(97), new Course(79)});

    private void unUseFlatMap() {
        Observable.just(s1, s2).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                for (int i = 0; i < student.course.length; i++) {
                    Log.e(TAG, "unUseFlatMap打印学生成绩:" + student.course[i].score);
                }
            }
        });
    }

    private void useFlatMap1() {
        Observable.just(s1, s2).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.course);
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.e(TAG, "useFlatMap1打印学生成绩:score=" + course.score);
            }
        });
    }

    private void useFlatMap2(){
        Observable.just(s1,s2).flatMap(new Func1<Student, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Student student) {
                return Observable.from(student.course).map(new Func1<Course, Integer>() {
                    @Override
                    public Integer call(Course course) {
                        return course.score;
                    }
                });
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e(TAG, "useFlatMap2打印学生成绩:score=" + integer);
            }
        });
    }

    private class Student {
        String name;
        Course course[];

        Student(String name, Course course[]) {
            this.name = name;
            this.course = course;
        }
    }

    private class Course {
        int score;

        private Course(int c) {
            this.score = c;
        }
    }

}
