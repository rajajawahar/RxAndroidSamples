package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.silicon.rxjavaexample.model.Customer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class DebounceActivity extends AppCompatActivity {


    private EditText mSearchBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchBox = (EditText) findViewById(R.id.editText);


        Observable<TextViewTextChangeEvent> textViewTextChangeEventObservable =
                RxTextView.textChangeEvents(mSearchBox).
                        debounce(5000, TimeUnit.MILLISECONDS).filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return true;

                    }
                }).observeOn(AndroidSchedulers.mainThread());
        textViewTextChangeEventObservable.subscribe(_getSearchObserver());



    }

    private Observer<TextViewTextChangeEvent> _getSearchObserver() {
        return new Observer<TextViewTextChangeEvent>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e.getLocalizedMessage(), "--------- Woops on error!");
            }

            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                Toast.makeText(DebounceActivity.this, onTextChangeEvent.text().toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void sampleRXJava() {

        Subscriber subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

                System.out.print("Completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.print("Throwable");
            }

            @Override
            public void onNext(Integer o) {
                System.out.print("Next" + o);
            }
        };
        Observable.just(1, 2, 3).subscribe(subscriber);
        removingEvenNumbers();

    }

    private void removingEvenNumbers() {
        Observable.just(1, 2, 3, 4, 5, 6) // add more numbers
                .filter(integer -> {
                    int value = integer % 2;
                    return value == 1;
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Complete!");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("onNext: " + value);
                    }
                });
    }





}
