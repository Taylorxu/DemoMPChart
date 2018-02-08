package com.mpchart.xu.mpchart;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mpchart.xu.mpchart.databinding.ActivityMultiLineChartBinding;

import java.util.ArrayList;

public class MultiLineChartActivity extends DemoBase implements OnChartValueSelectedListener {

    ActivityMultiLineChartBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, MultiLineChartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi_line_chart);
        invaliView();
    }

    private void invaliView() {
        binding.chart1.setOnChartValueSelectedListener(this);
        binding.chart1.setViewPortOffsets(100, 50, 20, 100);
        binding.chart1.setDrawGridBackground(false);
        binding.chart1.getDescription().setEnabled(false);
        binding.chart1.setDrawBorders(false);//去掉边框
        binding.chart1.getLegend().setEnabled(false);//去掉图例


        binding.chart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        binding.chart1.getXAxis().setDrawGridLines(false);
        binding.chart1.getAxisRight().setEnabled(false);
        binding.chart1.getAxisLeft().setDrawGridLines(true);
        binding.chart1.getAxisLeft().setGridDashedLine(new DashPathEffect(new float[]{8,10,8,10},0));

        binding.chart1.setTouchEnabled(true);
        binding.chart1.setDragEnabled(true);
        binding.chart1.setScaleEnabled(true);

        binding.chart1.setPinchZoom(false);

        setData();
    }

    private int[] mColors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1]

    };


    private void setData() {

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry>[] arrayLists = new ArrayList[]{values, values1};
        for (int z = 0; z < 2; z++) {
            if (z == 0) {
                //Entry 类是每条线在x、y 轴 上的数值
                for (int i = 0; i < 20; i++) {
                    float mult = 30 / 2f;
                    float val = (float) (Math.random() * mult) + 50;
                    arrayLists[z].add(new Entry(i, (float) val));
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    double val = (Math.random() * 30) + 10;
                    arrayLists[z].add(new Entry(i, (float) val));
                }
            }

            //每个LineDataSet 代表着一条线
            LineDataSet d = new LineDataSet(arrayLists[z], "DataSet " + (z + 1));
            d.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = mColors[z];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);
        }


        LineData data = new LineData(dataSets);
        binding.chart1.setData(data);
        binding.chart1.animateXY(3000,3000);
        binding.chart1.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
