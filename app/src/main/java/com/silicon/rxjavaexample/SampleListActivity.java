package com.silicon.rxjavaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;

public class SampleListActivity extends AppCompatActivity {


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
}
