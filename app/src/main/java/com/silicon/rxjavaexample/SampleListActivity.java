package com.silicon.rxjavaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;

public class SampleListActivity extends AppCompatActivity {


    private static final String TAG = SampleListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }


    public void operation(View v) {
        switch (v.getId()) {
            case R.id.debounce_operation:
                startActivity(new Intent(this, DebounceActivity.class));
                break;
            case R.id.combine_latest:
                startActivity(new Intent(this, CombineLatestActivity.class));
                break;
        }

    }

//    private void sgaga() {
//
//        MathObservable.averageInteger(Observable.just(1, 2, 3, 4)).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "call: " + integer);
//            }
//        });
//    }

}
