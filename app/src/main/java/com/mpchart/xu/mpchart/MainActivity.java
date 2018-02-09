package com.mpchart.xu.mpchart;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mpchart.xu.mpchart.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
//                CubicLineChartActivity.start(this);
                MultiLineChartActivity.start(this);
                break;
            case R.id.button2:
                PieChartActivity.start(this);
                break;
        }
    }
}
