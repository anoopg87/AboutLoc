package com.android.app.aboutloc.ui.view;
import android.support.v4.widget.SwipeRefreshLayout;
public interface FactsListFragmentView {
     SwipeRefreshLayout getSwipeRefreshLayout();
     void setServerError(String error);
     void setTitle(String title);
}
