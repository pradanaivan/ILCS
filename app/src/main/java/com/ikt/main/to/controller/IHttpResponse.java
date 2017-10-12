package com.ikt.main.to.controller;

import android.content.Context;

public interface IHttpResponse {

	public void onStarted(Context context, int pid);

	public void onSuccess(Context context, int pid, Object result);

	public void onFailed(Context context, int pid, Object result);

	public void onCompleted(Context context, int pid);
}
