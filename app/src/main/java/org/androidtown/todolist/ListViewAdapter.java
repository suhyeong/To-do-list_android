package org.androidtown.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todolistview_item, parent, false);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_checkbox);
        TextView todolist_text = (TextView) convertView.findViewById(R.id.to_do_list_text);
        TextView todolisttime_text = (TextView) convertView.findViewById(R.id.to_do_list_time_text);

        ListViewItem listViewItem = listViewItemArrayList.get(position);

        todolist_text.setText(listViewItem.getTo_do_list());
        todolisttime_text.setText(listViewItem.getTo_do_list_time());

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

    public void addItem(String todolist, String todolist_time) {
        ListViewItem item = new ListViewItem();

        item.setTo_do_list(todolist);
        item.setTo_do_list_time(todolist_time);

        listViewItemArrayList.add(item);
    }
}
