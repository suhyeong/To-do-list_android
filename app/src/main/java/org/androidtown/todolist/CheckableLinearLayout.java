package org.androidtown.todolist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox checkBox = findViewById(R.id.list_checkbox);
        if(checkBox.isChecked() != checked) {
            checkBox.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        CheckBox checkBox = findViewById(R.id.list_checkbox);
        return checkBox.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox checkBox = findViewById(R.id.list_checkbox);
        setChecked(checkBox.isChecked() ? false : true);
    }
}
