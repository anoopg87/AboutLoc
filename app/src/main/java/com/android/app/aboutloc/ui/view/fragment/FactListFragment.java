package com.android.app.aboutloc.ui.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.aboutloc.Application;
import com.android.app.aboutloc.R;
import com.android.app.aboutloc.databinding.FactsListFragmentBinding;
import com.android.app.aboutloc.ui.view.FactsListFragmentView;
import com.android.app.aboutloc.ui.viewmodel.FactListFragmentViewModel;
import com.android.app.aboutloc.util.ConnectivityReceiver;

public class FactListFragment extends Fragment implements FactsListFragmentView,ConnectivityReceiver.ConnectivityReceiverListener {
    private static final int LAYOUT_ID= R.layout.facts_list_fragment;
    private FactsListFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // Binding the layout with the FactListFragmentViewModel
        binding= DataBindingUtil.inflate(inflater,LAYOUT_ID,container,false);
        FactListFragmentViewModel viewModel=new FactListFragmentViewModel(getActivity(),this);
        binding.setHandler(viewModel);
        // setting the connectivityChangeListener for the fragment
        Application.setConnectivityChangeListener(this);
        return binding.getRoot();
    }

    // Returning the SwipeRefreshLayout for handle the events from viewModel
    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return binding.swipeRefreshLayout;
    }

    // Setting up server error with snack bar
    @Override
    public void setServerError(String error) {
        showSnackBar(error);
    }

    // Set the Title for the parent activity
    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    private void showSnackBar(String message){
        Snackbar snackbar = Snackbar
                .make( binding.itemsRecyclerView,message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    // Callback for the connection error
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(!isConnected)showSnackBar(Application.getStringRes(R.string.connection_error));
        getSwipeRefreshLayout().setRefreshing(false);
    }
}
