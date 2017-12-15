package com.dell.wearher_app.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dell.wearher_app.Model.Day;
import com.dell.wearher_app.R;
import com.dell.wearher_app.adapters.DayAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyForecastActivity extends AppCompatActivity  {


    private Day[] mDays;
    @BindView(R.id.list)ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeak=mDays[position].getDayOfTheWeek();
                String highTemp=mDays[position].getTemperatureMax()+"";
                String summary=mDays[position].getSummary();
                String message=String.format("On %S the high temprature is %S and it will be %S ",
                        dayOfTheWeak,
                        highTemp,
                        summary);
                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
