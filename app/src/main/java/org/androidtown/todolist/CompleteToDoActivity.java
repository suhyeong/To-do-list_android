package org.androidtown.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

public class CompleteToDoActivity extends AppCompatActivity {

    ListView complete_todolist;
    Complete_ToDo_ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_to_do);

        complete_todolist = findViewById(R.id.complete_to_do_list);
        listViewAdapter = new Complete_ToDo_ListViewAdapter();
        complete_todolist.setAdapter(listViewAdapter);
        complete_todolist.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

    }
}
