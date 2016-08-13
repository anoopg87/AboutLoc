package com.android.app.aboutloc.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.app.aboutloc.BR;

import java.util.List;


public class Facts extends BaseObservable {

    private String title;
    private List<Row> rows;

    public Facts(List<Row> rows, String title) {
        this.rows = rows;
        this.title = title;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        notifyPropertyChanged(BR.rows);
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }


}
