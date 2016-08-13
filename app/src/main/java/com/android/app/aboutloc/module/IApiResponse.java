package com.android.app.aboutloc.module;

public interface IApiResponse<T> {
    /*
    Interface for Webservice callBack
     */
    void onSuccess(T result);
    void onError(String error);
}
