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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

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
        printValues();


        RxTextView.textChangeEvents(editText).subscribe(textViewTextChangeEvent -> {

            onNewTextChange(textViewTextChangeEvent.text());
        });
        RxView.clicks(button).subscribe(o -> {
            Log.d("vssbs", "submit button clicked!");
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
            return s;
        });
    }


    public void onNewTextChange(CharSequence charSequence) {
        Toast.makeText(WidgetsActivity.this, charSequence, Toast.LENGTH_SHORT).show();
    }


    private void printValues() {
//        NumberFormat df = NumberFormat.getCurrencyInstance();
//        dfs.setCurrencySymbol("USD ");
//        dfs.setGroupingSeparator('.');
//        dfs.setMonetaryDecimalSeparator('.');
//        ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
//        Log.d(TAG, "printValues: " + df.format(34343.35353));
        Currency currency = Currency.getInstance("USD");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        decimalFormat.setCurrency(currency);
        Log.d(TAG, "printValues: " + decimalFormat.format(35525.15151));
        Log.d(TAG, "printValues: " + currency.getSymbol());
    }

}
