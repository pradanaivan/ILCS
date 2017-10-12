package com.ikt.main.to.parser;

import android.content.Context;

import com.ikt.main.to.controller.IParser;

public class DefaultParser implements IParser {
    private String errorMsg;

    @Override
    public void parser(Context context, int pid, String json) {

    }

    @Override
    public void setError(String errorMessage) {
        this.errorMsg = errorMessage;
    }

    @Override
    public boolean isError() {
        return null != errorMsg;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMsg;
    }

}
