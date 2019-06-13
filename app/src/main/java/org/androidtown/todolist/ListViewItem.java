package org.androidtown.todolist;

import android.widget.CheckBox;
import android.widget.TextView;

public class ListViewItem {
    private CheckBox to_do_list_checkbox;
    private String to_do_list, to_do_list_time;

    public void setTo_do_list_checkbox(CheckBox to_do_list_checkbox) {
        this.to_do_list_checkbox = to_do_list_checkbox;
    }

    public void setTo_do_list(String to_do_list) {
        this.to_do_list = to_do_list;
    }

    public void setTo_do_list_time(String to_do_list_time) {
        this.to_do_list_time = to_do_list_time;
    }

    public CheckBox getTo_do_list_checkbox() {
        return this.to_do_list_checkbox;
    }

    public String getTo_do_list() {
        return this.to_do_list;
    }

    public String getTo_do_list_time() {
        return this.to_do_list_time;
    }
}
