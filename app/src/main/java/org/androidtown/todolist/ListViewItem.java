package org.androidtown.todolist;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListViewItem {
    private CheckBox to_do_list_checkbox;
    private String to_do_list, to_do_list_time, to_do_list_date;
    private ImageButton delete_btn;

    public void setTo_do_list_checkbox(CheckBox to_do_list_checkbox) {
        this.to_do_list_checkbox = to_do_list_checkbox;
    }

    public void setTo_do_list(String to_do_list) {
        this.to_do_list = to_do_list;
    }

    public void setTo_do_list_time(String to_do_list_time) {
        this.to_do_list_time = to_do_list_time;
    }

    public void setTo_do_list_date(String to_do_list_date) {
        this.to_do_list_date = to_do_list_date;
    }

    public void setDelete_btn(ImageButton delete_btn) {
        this.delete_btn = delete_btn;
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

    public String getTo_do_list_date() { return this.to_do_list_date; }

    public ImageButton getDelete_btn() { return this.delete_btn; }
}
