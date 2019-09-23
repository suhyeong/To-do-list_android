package org.androidtown.todolist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView today_weekday, today_month, todolist_count;
    ListView to_do_list;
    ToDo_ListViewAdapter listViewAdapter;
    FloatingActionButton open_menu, add_list, complete_list;
    String todayDate, diffDays_result;
    ConstraintLayout layout;

    private Animation fab_open, fab_close, rotate_dropdown, rotate_dropup;
    private boolean isFabOpen = false;

    String todolist_text, todolist_time, todolist_date;

    ArrayList<String> SelectedItem = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_layout);
        Date todayTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayformat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat dayformat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthformat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat todayformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String weekday = weekdayformat.format(todayTime);
        String day = dayformat.format(todayTime);
        String month = monthformat.format(todayTime);
        todayDate = todayformat.format(todayTime);

        to_do_list = findViewById(R.id.to_do_list);
        listViewAdapter = new ToDo_ListViewAdapter();
        to_do_list.setAdapter(listViewAdapter);
        to_do_list.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        to_do_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listViewAdapter.getItem(position).
            }
        });

        today_weekday = findViewById(R.id.today_weekday);
        today_month = findViewById(R.id.today_month);
        todolist_count = findViewById(R.id.todolist_count);
        today_month.setText(getMonth(month));

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
                Intent intent = new Intent(this, NewToDoActivity.class);
                startActivityForResult(intent,1000);
                break;
            case R.id.complete_list:
                toggleFab();
                Intent complete_intent = new Intent(this, CompleteToDoActivity.class);
                startActivity(complete_intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    todolist_text = data.getStringExtra("todolist_text");
                    todolist_time = TimeConvert(data.getStringExtra("todolist_time"));
                    todolist_date = DateConvert(data.getStringExtra("todolist_date"));
                    listViewAdapter.addItem(todolist_text, todolist_time, todolist_date);
                    listViewAdapter.notifyDataSetChanged();
                    String todolistcount_str = listViewAdapter.getCount()+" Tasks";
                    SpannableStringBuilder spannableStringBuilder_ = new SpannableStringBuilder(todolistcount_str);
                    spannableStringBuilder_.setSpan(new StyleSpan(Typeface.BOLD), 0, todolistcount_str.indexOf(" "), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    todolist_count.setText(spannableStringBuilder_);
                    break;
            }
        }
    }

    public String TimeConvert(String todolist_time) {
        String ampm = null;
        String result = null;

        int hour = Integer.parseInt(todolist_time.substring(0,todolist_time.indexOf(":")));
        int minute = Integer.parseInt(todolist_time.substring(todolist_time.indexOf(":")+1,todolist_time.length()));

        if(hour > 12) {
            ampm = "PM";
            int hour2 = hour - 12;
            int hour_length = (int)(Math.log10(hour2)+1);
            int min_length = (int)(Math.log10(minute)+1);
            if(hour_length == 1) {
                if(min_length == 1 || hour2 == 0 || minute == 0) {
                    result = "0"+ hour2 + ":0" + minute + " " + ampm;
                } else
                    result = "0"+ hour2 + ":" + minute + " " + ampm;
            } else {
                if(min_length == 1 || minute == 0) {
                    result = hour2 + ":0" + minute + " " + ampm;
                } else
                    result = hour2 + ":" + minute + " " + ampm;
            }
        } else if(hour == 12) {
            ampm = "PM";
            int hour2 = hour - 12;
            int min_length = (int)(Math.log10(minute)+1);
            if(min_length == 1 || hour2 == 0 || minute == 0) {
                result = "0"+ hour2 + ":0" + minute + " " + ampm;
            } else
                result = "0"+ hour2 + ":" + minute + " " + ampm;
        } else if(hour == 0) {
            ampm = "AM";
            int min_length = (int)(Math.log10(minute)+1);
            if(min_length == 1 || minute == 0) {
                result = "0"+ hour + ":0" + minute + " " + ampm;
            } else
                result = "0"+ hour + ":" + minute + " " + ampm;
        } else {
            ampm = "AM";
            int hour_length = (int)(Math.log10(hour)+1);
            int min_length = (int)(Math.log10(minute)+1);
            if(hour_length == 1) {
                if(min_length == 1 || hour == 0 || minute == 0) {
                    result = "0"+ hour + ":0" + minute + " " + ampm;
                } else
                    result = "0"+ hour + ":" + minute + " " + ampm;
            } else {
                if(min_length == 1 || minute == 0) {
                    result = hour + ":0" + minute + " " + ampm;
                } else
                    result = hour + ":" + minute + " " + ampm;
            }
        }

        return result;
    }


    public String DateConvert(String todolist_date) {
        SimpleDateFormat simpleDateFormat_ = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date firstday = simpleDateFormat_.parse(todayDate);
            Date secondday = simpleDateFormat_.parse(todolist_date);

            long diff = firstday.getTime() - secondday.getTime();
            long diffDays = diff / (24*60*60*1000);
            diffDays_result = "D" + diffDays;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return diffDays_result;
    }
}
