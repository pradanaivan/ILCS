package com.ikt.main.to.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.ikt.main.to.IKTApplication;
import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.parser.AuthParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/8/16.
 */
public class LoginActivity extends Activity implements View.OnClickListener, IHttpResponse {

    @Bind(R.id.edtUsername)
    MaterialEditText edtUsername;
    @Bind(R.id.edtPassword)
    MaterialEditText edtPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;

    private IKTApplication aController;
    // Asyntask
    private AsyncTask<Void, Void, Void> mRegisterTask;
    public static String name;
    public static String email;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        aController = IKTApplication.getInstance();
    }


    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            String username = edtUsername.getText().toString();
            String pwd = edtPassword.getText().toString();

            if (username.isEmpty() || pwd.isEmpty()) {
                Utility.validateEditText(edtUsername, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtPassword, getResources().getString(R.string.mandatory));
            } else {
                auth();
            }
        }
    }

    private void auth() {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteProfile();
        String username = edtUsername.getText().toString();
        String pwd = edtPassword.getText().toString();
        String md5Pwd = Utility.getMD5("1" + username + pwd);
        HttpTask task = new HttpTask(this, Config.URL_LOGIN, 1001, Config.POST, this, AuthParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("username", username));
        post.add(new BasicNameValuePair("password", md5Pwd));
        post.add(new BasicNameValuePair("role", "1"));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(this);
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof AuthParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                DBHelper db = new DBHelper(this);
                ProfileObject object = db.getProfile();
                name = object.getName();
                email = object.getUsername();
//                goToHome();
                pushRegister();

            }
        }
    }

    void goToHome(){
        Intent i = new Intent(this, ActivityCarousel.class);
        startActivity(i);
        finish();
    }

    private void pushRegister(){
        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);

        // Make sure the manifest permissions was properly set
        GCMRegistrar.checkManifest(this);

        // Register custom Broadcast receiver to show messages on activity
        registerReceiver(mHandleMessageReceiver, new IntentFilter(
                Config.DISPLAY_MESSAGE_ACTION));

        // Get GCM registration id
        final String regId = GCMRegistrar.getRegistrationId(this);

        // Check if regid already presents
        if (regId.equals("")) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Verify device");
            dialog.show();
            // Register with GCM
            GCMRegistrar.register(this, Config.CLIENT_ID);

        } else {

            // Device is already registered on GCM Server
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                goToHome();
            } else {

                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.

                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {



                    @Override
                    protected void onPreExecute() {
                        dialog = new ProgressDialog(context);
                        dialog.setMessage("Verify device");
                        dialog.show();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {

                        // Register on our server
                        // On server creates a new user
                        aController.register(context, name, email, regId);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                        dialog.dismiss();
                    }

                };

                // execute AsyncTask
                mRegisterTask.execute(null, null, null);
                goToHome();
            }
        }
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        if (parser.isError()) {
            Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(Config.EXTRA_MESSAGE);
            aController.acquireWakeLock(LoginActivity.this);

            // Showing received message
//            Toast.makeText(LoginActivity.this, "New Message: " + newMessage, Toast.LENGTH_LONG).show();

            // Releasing wake lock
            aController.releaseWakeLock();dialog.dismiss();
            dialog.dismiss();
            goToHome();
        }
    };

    @Override
    protected void onDestroy() {
        // Cancel AsyncTask
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        try {
            // Unregister Broadcast Receiver
            unregisterReceiver(mHandleMessageReceiver);

            //Clear internal resources.
//            GCMRegistrar.onDestroy(this);

        } catch (Exception e) {
            Log.e(Config.TAG, "> " + e.getMessage());
        }
        super.onDestroy();
    }

    @Override
    public void onCompleted(Context context, int pid) {
    }

}
