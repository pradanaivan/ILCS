package com.ikt.main.to.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.ikt.main.to.component.CustomUpdateDialog;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.SimpleObject;
import com.ikt.main.to.parser.CheckUpdateParser;
import com.ikt.main.to.parser.DriverParser;
import com.ikt.main.to.parser.TruckParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arifin on 2/7/16.
 */
public class SplashActivity extends Activity implements IHttpResponse, TapView {

    private DBHelper dbHelper;
    private String orgId;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);
        dbHelper = new DBHelper(this);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        isLogin = PreferenceManagers.hasData(Config.IS_LOGIN, this);
        checkUpdate();
    }

    private void getDrivers() {
        HttpTask task = new HttpTask(this, Config.URL_DRIVER, 1001, Config.POST, this, DriverParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        DriverObject object = dbHelper.getLastDriver();
        if(object != null)
            post.add(new BasicNameValuePair(Config.KEY_LAST_ID, object.getDriverId() + ""));
        else
            post.add(new BasicNameValuePair(Config.KEY_LAST_ID,  "0"));
        task.setPostData(post);
        task.setEnabledProgressDialog(false);
        task.setCancelableProgressDialog(false);
        HttpManager.getInstance().doRequest(task);
    }

    private void getTrucks() {
        HttpTask task = new HttpTask(this, Config.URL_TRUCK, 1001, Config.POST, this, TruckParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_LAST_ID, dbHelper.getTruckCount() + ""));
        task.setPostData(post);
        task.setEnabledProgressDialog(false);
        task.setCancelableProgressDialog(false);
        HttpManager.getInstance().doRequest(task);
    }

    private void checkUpdate() {
        HttpTask task = new HttpTask(this, Config.URL_CHECK_VERSION, 1003, Config.POST, this, CheckUpdateParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("version", Config.getVersion()));
        task.setPostData(post);
        task.setEnabledProgressDialog(false);
        task.setCancelableProgressDialog(false);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof DriverParser){

        }else if(result instanceof TruckParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                if(!isLogin) {
                    goToLogin();
                }else {
                    goToHome();
                }
            }
        }else if(result instanceof CheckUpdateParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                SimpleObject object = VectorModel.getInstance().getCheckUpdateObject().get(0);
                switch (object.getStatus()){
                    case 0:
                        runNormally();
                        break;
                    case 1:
                        Utility.createDialogForceUpdate(this,"Update", "You must update first to use this application", this);
                        break;
                    case 2:
                        Utility.createDialogUpdate(this,"Update", "Do you want to update ?", 2, this);
                        //showCustomDialog(object.getStatus());
                        break;
                }
            }
        }
    }

    private void showCustomDialog(int status){
        CustomUpdateDialog cdd = new CustomUpdateDialog(this, status, this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    private void goToLogin(){
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToHome(){
        Intent intent = new Intent(SplashActivity.this, ActivityCarousel.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(this,parser.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {
    }

    @Override
    public void setAction(int type, int position, String name) {
        if(type == 98 && position == 2){
            runNormally();
        }else if(type == 99 && position == 2){
            openPlayStore();
        }else if(type == 1 && position == 1){
            openPlayStore();
        }
    }

    private void openPlayStore() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        finish();
    }

    private void runNormally(){
        if (orgId != null) {
            getDrivers();
            getTrucks();
        } else {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (!isLogin) {
                            goToLogin();
                        } else {
                            goToHome();
                        }
                    }
                }
            };
            timerThread.start();
        }
    }
}
