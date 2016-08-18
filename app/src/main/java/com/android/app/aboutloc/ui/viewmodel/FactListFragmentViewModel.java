package com.android.app.aboutloc.ui.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.android.app.aboutloc.Application;
import com.android.app.aboutloc.BR;
import com.android.app.aboutloc.R;
import com.android.app.aboutloc.adapter.RecyclerViewBindingAdapter;
import com.android.app.aboutloc.adapter.RecyclerViewConfiguration;
import com.android.app.aboutloc.model.Facts;
import com.android.app.aboutloc.model.Row;
import com.android.app.aboutloc.module.ApiImplementation;
import com.android.app.aboutloc.module.IApiResponse;
import com.android.app.aboutloc.ui.view.FactsListFragmentView;
import com.android.app.aboutloc.util.ConnectivityReceiver;

import java.util.ArrayList;
import java.util.List;

public class FactListFragmentViewModel extends BaseObservable {

    private final Context context;
    private final FactsListFragmentView factsListFragmentView;
    private RecyclerViewBindingAdapter<Row> factsListAdapter;
    @SuppressWarnings("WeakerAccess")
    public final RecyclerViewConfiguration factListConfig = new RecyclerViewConfiguration();

    //RefreshListener for binding
    @SuppressWarnings("unused")
    public final SwipeRefreshLayout.OnRefreshListener onRefreshListener= () -> {
        invokeInfoApi();
    };
    public FactListFragmentViewModel(Context context, FactsListFragmentView factsListFragmentView) {
        this.context = context;
        this.factsListFragmentView = factsListFragmentView;
        setFactsListAdapter();
        startInitialLoading();
    }

    // setting the recycler view adapter with the null dataSet
    private void setFactsListAdapter() {
        factListConfig.setItemAnimator(new DefaultItemAnimator());
        factListConfig.setLayoutManager(new LinearLayoutManager(context));
        List<Row> itemRow = new ArrayList<>();
        factsListAdapter = new RecyclerViewBindingAdapter<>(R.layout.facts_row_layout, BR.rowItem, itemRow);
        factListConfig.setAdapter(factsListAdapter);
    }

    private void startInitialLoading() {
    // start the initial loading with swipe refresh layout
        factsListFragmentView.getSwipeRefreshLayout().post(() -> {
            factsListFragmentView.getSwipeRefreshLayout().setRefreshing(true);
            invokeInfoApi();
        });
    }

    private void invokeInfoApi() {
        // invoking the InfoApi by checking the internet connection
        if (ConnectivityReceiver.isConnected()) {

            new ApiImplementation().invokeGetInformationApi(new IApiResponse<Facts>() {
                @Override
                public void onSuccess(Facts facts) {
                    // set the toolbar title from the webservice
                    factsListFragmentView.setTitle(facts.getTitle());
                    factsListAdapter.clearDataSet();
                    factsListAdapter.addItems(facts.getRows());
                    factsListFragmentView.getSwipeRefreshLayout().setRefreshing(false);
                }

                @Override
                public void onError(String error) {
                    // set error message
                    factsListFragmentView.getSwipeRefreshLayout().setRefreshing(false);
                    factsListFragmentView.setServerError(error);
                }
            });

        } else {
            // set connectivity error message
            factsListFragmentView.getSwipeRefreshLayout().setRefreshing(false);
            factsListFragmentView.setServerError(Application.getStringRes(R.string.connection_error));
        }
    }


}
