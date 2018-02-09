package com.mpchart.xu.mpchart;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.mpchart.xu.mpchart.databinding.ActivityPeChartBinding;

import java.util.ArrayList;

public class PieChartActivity extends DemoBase {
    ActivityPeChartBinding peChartBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, PieChartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pe_chart);
        peChartBinding = DataBindingUtil.setContentView(this, R.layout.activity_pe_chart);
        invialView();
    }

    private void invialView() {

        peChartBinding.viewPieChart.setUsePercentValues(true);
        peChartBinding.viewPieChart.getDescription().setEnabled(false);
        peChartBinding.viewPieChart.setExtraOffsets(5, 10, 5, 5);

        peChartBinding.viewPieChart.setDragDecelerationFrictionCoef(0.95f);

        peChartBinding.viewPieChart.setDrawHoleEnabled(true);//置空中间,否则是扇形图
        peChartBinding.viewPieChart.setHoleColor(Color.WHITE);
        peChartBinding.viewPieChart.setDrawCenterText(true);
        peChartBinding.viewPieChart.setCenterTextTypeface(mTfLight);
        //先获取数据，将数据作为参数传过去
        peChartBinding.viewPieChart.setCenterText(generateCenterSpannableText());

        peChartBinding.viewPieChart.setTransparentCircleColor(Color.WHITE);
        peChartBinding.viewPieChart.setTransparentCircleAlpha(110);

        peChartBinding.viewPieChart.setHoleRadius(58f);
        peChartBinding.viewPieChart.setTransparentCircleRadius(61f);


        // 旋转
        peChartBinding.viewPieChart.setRotationAngle(0);
        peChartBinding.viewPieChart.setRotationEnabled(true);
        peChartBinding.viewPieChart.setHighlightPerTapEnabled(true);


        setData(2, 100);

        peChartBinding.viewPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

    }
    private int[] mColors = new int[]{
            Color.BLUE,Color.GRAY

    };

    private void setData(int count, float range) {
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(mColors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(false);//不在环形上显示数据
        peChartBinding.viewPieChart.setData(data);

        peChartBinding.viewPieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("及时率\n75.3%");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 7, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 3, 0);

        return s;
    }

}
