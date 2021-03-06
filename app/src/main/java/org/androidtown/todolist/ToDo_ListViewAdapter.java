package org.androidtown.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDo_ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    public ToDo_ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todolistview_item, parent, false);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_checkbox);
        final TextView todolist_text = convertView.findViewById(R.id.to_do_list_text);
        final TextView todolisttime_text = (TextView) convertView.findViewById(R.id.to_do_list_time_text);
        final TextView todolistdate_text = (TextView) convertView.findViewById(R.id.to_do_list_d_day_text);
        final View todolist_check_anim = (View) convertView.findViewById(R.id.listview_check_anim);

        final ListViewItem listViewItem = listViewItemArrayList.get(position);

        todolist_text.setText(listViewItem.getTo_do_list());
        final ColorStateList nomalcolor = todolist_text.getTextColors();
        final ColorStateList date_nomalcolor = todolistdate_text.getTextColors();
        todolisttime_text.setText(listViewItem.getTo_do_list_time());
        todolistdate_text.setText(listViewItem.getTo_do_list_date());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Animation listanim = AnimationUtils.loadAnimation(context, R.anim.listview_check);
                    todolist_check_anim.setVisibility(View.VISIBLE);
                    todolist_check_anim.setAnimation(listanim);
                    todolist_text.setTextColor(Color.LTGRAY);
                    todolisttime_text.setTextColor(Color.LTGRAY);
                    todolistdate_text.setTextColor(Color.LTGRAY);

                    /*
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("todolist_do", todolist_text.getText().toString());
                    bundle.putString("todolist_time", todolisttime_text.getText().toString());
                    bundle.putString("todolist_date", todolistdate_text.getText().toString());
                    intent.putExtras(bundle);
                    */
                } else {
                    Animation listanimun = AnimationUtils.loadAnimation(context, R.anim.listview_uncheck);
                    todolist_check_anim.setVisibility(View.INVISIBLE);
                    todolist_check_anim.setAnimation(listanimun);
                    todolist_text.setTextColor(nomalcolor);
                    todolisttime_text.setTextColor(date_nomalcolor);
                    todolistdate_text.setTextColor(date_nomalcolor);
                }
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    public void addItem(String todolist, String todolist_time, String todolist_date) {
        ListViewItem item = new ListViewItem();

        item.setTo_do_list(todolist);
        item.setTo_do_list_time(todolist_time);
        item.setTo_do_list_date(todolist_date);

        listViewItemArrayList.add(item);
    }

    public void removeItem(int position) {
        listViewItemArrayList.remove(position);
    }
}
