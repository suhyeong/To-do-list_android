package org.androidtown.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.CalendarWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NewToDoActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener {

    Toolbar toolbar;
    EditText to_do_text;
    MaterialCalendarView materialCalendarView;
    TimePicker timePicker;
    String text, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        toolbar = findViewById(R.id.new_to_do_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        to_do_text = findViewById(R.id.user_to_do_text);

        materialCalendarView = findViewById(R.id.to_do_calendar);
        materialCalendarView.setTopbarVisible(true);

        CharSequence[] charSequences = new CharSequence[]{"SUN","MON","TUE","WED","THE","FRI","SAT"};
        materialCalendarView.setWeekDayLabels(charSequences);

        SimpleDateFormat title_simpleDateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        DateFormatTitleFormatter dateFormatTitleFormatter = new DateFormatTitleFormatter(title_simpleDateFormat);
        materialCalendarView.setTitleFormatter(dateFormatTitleFormatter);

        timePicker = findViewById(R.id.to_do_time);
        timePicker.setOnTimeChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_to_do_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.new_done:
                text = to_do_text.getText().toString();
                Date date_ = materialCalendarView.getSelectedDate().getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = simpleDateFormat.format(date_);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("todolist_text", text);
                resultIntent.putExtra("todolist_time", time);
                resultIntent.putExtra("todolist_date", date);
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        time = String.valueOf(hourOfDay)+":"+String.valueOf(minute);
    }
}
