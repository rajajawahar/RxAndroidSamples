package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class WidgetsActivity extends AppCompatActivity {


    private static final String TAG = WidgetsActivity.class.getSimpleName();


    @BindView(R.id.editText2)
    EditText editText;
    @BindView(R.id.button)
    Button button;

    private ArrayList<String> strings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        RxTextView.textChangeEvents(editText).subscribe(textViewTextChangeEvent -> {

            onNewTextChange(textViewTextChangeEvent.text());
        });
        RxView.clicks(button).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d("vssbs", "submit button clicked!");
            }
        });

        strings.clear();
        strings.add("First");
        strings.add("Second");
        strings.add("Third");
        strings.add("Fifth");
        Observable.just(strings).last().flatMapIterable(new Func1<ArrayList<String>, Iterable<String>>() {
            @Override
            public Iterable<String> call(ArrayList<String> strings) {
                return strings;
            }
        }).map(s -> {
            Log.d(TAG, "call: " + s);
            return s;
        });
    }


    public void onNewTextChange(CharSequence charSequence) {
        Toast.makeText(WidgetsActivity.this, charSequence, Toast.LENGTH_SHORT).show();
    }


}
