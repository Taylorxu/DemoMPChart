package com.mpchart.xu.mpchart;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.mpchart.xu.mpchart.databinding.ActivityCubicLineChartBinding;

import java.util.ArrayList;

public class CubicLineChartActivity extends DemoBase {

    ActivityCubicLineChartBinding lineChartBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, CubicLineChartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineChartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cubic_line_chart);
        invaliView();
    }

    private void invaliView() {
        lineChartBinding.chart1.setViewPortOffsets(100, 50, 20, 100);
        lineChartBinding.chart1.setBackgroundColor(Color.rgb(104, 241, 175));
        lineChartBinding.chart1.getDescription().setEnabled(false);
        lineChartBinding.chart1.setTouchEnabled(true);
        lineChartBinding.chart1.setDragEnabled(true);
        lineChartBinding.chart1.setScaleEnabled(true);

        XAxis x = lineChartBinding.chart1.getXAxis();
        x.setTypeface(mTfLight);
        x.setTextColor(Color.RED);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setAxisLineColor(Color.BLACK);

        YAxis y = lineChartBinding.chart1.getAxisLeft();
        y.setTypeface(mTfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        lineChartBinding.chart1.getAxisRight().setEnabled(false);

        setData(50, 100);
        lineChartBinding.chart1.invalidate();
    }


    private void setData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 20;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (lineChartBinding.chart1.getData() != null &&
                lineChartBinding.chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChartBinding.chart1.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            lineChartBinding.chart1.getData().notifyDataChanged();
            lineChartBinding.chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            lineChartBinding.chart1.setData(data);
        }
    }

}
