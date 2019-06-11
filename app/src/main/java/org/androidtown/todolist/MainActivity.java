package org.androidtown.todolist;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView today_weekday, today_day, today_month, todolist_count;
    ListView to_do_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date todayTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayformat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat dayformat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthformat = new SimpleDateFormat("MM", Locale.getDefault());

        String weekday = weekdayformat.format(todayTime);
        String day = dayformat.format(todayTime);
        String month = monthformat.format(todayTime);

        to_do_list = findViewById(R.id.to_do_list);
        today_weekday = findViewById(R.id.today_weekday);
        today_day = findViewById(R.id.today_day);
        today_month = findViewById(R.id.today_month);
        todolist_count = findViewById(R.id.todolist_count);
        today_weekday.setText(getWeekday(weekday));
        today_day.setText(getDay(day));
        today_month.setText(getMonth(month));
        todolist_count.setText(to_do_list.getChildCount()+" Tasks");

        FloatingActionButton floatingActionButton = findViewById(R.id.add_list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String getMonth(String month) {
        String result = null;

        switch (month) {
            case "01":
                result = "January";
                break;
            case "02":
                result = "February";
                break;
            case "03":
                result = "March";
                break;
            case "04":
                result = "April";
                break;
            case "05":
                result = "May";
                break;
            case "06":
                result = "June";
                break;
            case "07":
                result = "July";
                break;
            case "08":
                result = "August";
                break;
            case "09":
                result = "September";
                break;
            case "10":
                result = "October";
                break;
            case "11":
                result = "November";
                break;
            case "12":
                result = "December";
                break;
        }
        return result;
    }

    public String getDay(String day) {
        int intday = Integer.parseInt(day);
        String result = null;

        if(intday >= 11 && intday <= 13){
            result = day.concat("th");
        }
        else {
            switch (intday % 10) {
                case 1:
                    result = day.concat("st");
                    break;
                case 2:
                    result = day.concat("nd");
                    break;
                case 3:
                    result = day.concat("rd");
                    break;
                default:
                    result = day.concat("th");
                    break;
            }
        }

        return result;
    }

    public String getWeekday(String weekday) {
        String result = null;
        switch (weekday) {
            case "월":
                result = "Monday,";
                break;
            case "화":
                result = "Tuesday,";
                break;
            case "수":
                result = "Wednesday,";
                break;
            case "목":
                result = "Thursday,";
                break;
            case "금":
                result = "Friday,";
                break;
            case "토":
                result = "Saturday,";
                break;
            case "일":
                result = "Sunday,";
                break;
        }

        return result;
    }
}
