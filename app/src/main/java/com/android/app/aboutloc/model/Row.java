package com.android.app.aboutloc.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.app.aboutloc.BR;


public class Row  extends BaseObservable  {

    private String title;
    private String description;
    private String imageHref;


    public Row(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    @Bindable
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
        notifyPropertyChanged(BR.imageHref);
    }


}
