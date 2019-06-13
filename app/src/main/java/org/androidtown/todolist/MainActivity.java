package org.androidtown.todolist;

import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView today_weekday, today_month, todolist_count;
    ListView to_do_list;
    ListViewAdapter listViewAdapter;
    FloatingActionButton open_menu, add_list, complete_list;

    private Animation fab_open, fab_close, rotate_dropdown, rotate_dropup;
    private boolean isFabOpen = false;

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
        listViewAdapter = new ListViewAdapter();
        to_do_list.setAdapter(listViewAdapter);
        today_weekday = findViewById(R.id.today_weekday);
        today_month = findViewById(R.id.today_month);
        todolist_count = findViewById(R.id.todolist_count);
        today_month.setText(getMonth(month));

        listViewAdapter.addItem("졸업작품 PPT 제출","10:00 AM");
        listViewAdapter.addItem("과제 제출","04:00 PM");

        String todolistcount_str = listViewAdapter.getCount()+" Tasks";
        SpannableStringBuilder spannableStringBuilder_ = new SpannableStringBuilder(todolistcount_str);
        spannableStringBuilder_.setSpan(new StyleSpan(Typeface.BOLD), 0, todolistcount_str.indexOf(" "), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        todolist_count.setText(spannableStringBuilder_);

        String weekdayNday = getWeekday(weekday).concat(" "+getDay(day));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(weekdayNday);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 0, weekdayNday.indexOf(",")+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        today_weekday.setText(spannableStringBuilder);

        open_menu = findViewById(R.id.open_menu);
        add_list = findViewById(R.id.add_list);
        complete_list = findViewById(R.id.complete_list);
        open_menu.setOnClickListener(this);
        add_list.setOnClickListener(this);
        complete_list.setOnClickListener(this);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_dropdown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_dropdown);
        rotate_dropup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_dropup);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_menu:
                toggleFab();
                break;
            case R.id.add_list:
                toggleFab();
                break;
            case R.id.complete_list:
                toggleFab();
                break;
        }
    }

    private void toggleFab() {
        if(isFabOpen) {
            open_menu.startAnimation(rotate_dropup);
            add_list.startAnimation(fab_close);
            complete_list.startAnimation(fab_close);
            add_list.setClickable(false);
            complete_list.setClickable(false);
            isFabOpen = false;
        } else {
            open_menu.startAnimation(rotate_dropdown);
            add_list.startAnimation(fab_open);
            complete_list.startAnimation(fab_open);
            add_list.setClickable(true);
            complete_list.setClickable(true);
            isFabOpen = true;
        }
    }
}
