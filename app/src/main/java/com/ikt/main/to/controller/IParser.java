package com.ikt.main.to.controller;

import android.content.Context;

public interface IParser {
    void parser(Context context, int pid, String json);

    void setError(String errorMessage);

    String getErrorMessage();

    boolean isError();
}