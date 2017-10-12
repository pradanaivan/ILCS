package com.ikt.main.to.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.CarouselMenuAdapter;
import com.ikt.main.to.component.Carousel;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.object.TicketObject;
import com.ikt.main.to.parser.TickerParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 5/1/16.
 */
public class ActivityCarousel extends BaseActivity implements IHttpResponse{
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.carousel)
    Carousel carousel;
    private ActionBar mActionBar;

    private String orgId,session,userId;

    @Override
    public boolean isAvailableSearchBtn() {
        return false;
    }

    @Override
    public boolean isAvailableMyAccBtn() {
        return true;
    }

    @Override
    public boolean isAvailableLangBtn() {
        return true;
    }

    @Override
    public boolean isAvailableNotifBtn() {
        return true;
    }

    @Override
    public boolean isAvailableSignout() {
        return true;
    }

    private String msg = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_layout);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            msg = extras.getString("msg");
        }
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        setToolBar();
        Carousel carousel = (Carousel) findViewById(R.id.carousel);

        carousel.setOnItemClickListener(new CarouselMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarouselMenuAdapter<?> parent, View view, int position, long id) {
//                if(position != 4 ) {
                    Intent i = new Intent(ActivityCarousel.this, HomeActivity.class);
                    i.putExtra("pos", position);
                    startActivity(i);
//                }
            }
        });
        setProfile();
        // Satrio (getTickets sementara dicoment agar tidak lemot, request dari Habib)
//        getTickets();
    }

    private void setProfile(){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteProfile();
        String json = PreferenceManagers.getData(Config.KEY_PROFILE, this);
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            String userId = obj.optString("USERID");
            String username = obj.optString("USERNAME");
            String name = obj.optString("NAME");
            String grupName = obj.optString("GROUPNAME");
            String orgId = obj.optString("ORG_ID");
            String orgCode = obj.optString("ORG_CODE");
            String session = obj.optString("SESSION_ID");
            String orgName = obj.optString("ORG_NAME");

            ProfileObject profileObject = new ProfileObject();
            profileObject.setName(name);
            profileObject.setSession(session);
            profileObject.setOrgName(orgName);
            profileObject.setOrgId(orgId);
            profileObject.setUserId(userId);
            profileObject.setUsername(username);
            profileObject.setOrgCode(orgCode);
            dbHelper.createProfile(profileObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(false);
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setTitle("");
        }
        toolbarTitle.setText(getResources().getString(R.string.home));
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        menu.findItem(R.id.action_search).setVisible(false);
//        menu.findItem(R.id.action_notif).setVisible(false);
//        menu.findItem(R.id.action_refresh).setVisible(false);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_notif).setVisible(false);
        menu.findItem(R.id.action_refresh).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_my_account){
            Intent i = new Intent(ActivityCarousel.this, HomeActivity.class);
            i.putExtra("pos", 5);
            startActivity(i);
        }else if(id == R.id.action_language){

        }
        return super.onOptionsItemSelected(item);
    }

    private void getTickets() {
//        ArrayList<TicketObjectModel> ticketObjects = VectorModel.getInstance().getAssignTicketObjects();
        ArrayList<TicketObject> ticketObjects = VectorModel.getInstance().getListAssignTicketObjects();
        HttpTask task = new HttpTask(this, Config.URL_GET_TICKET, 1001, Config.POST, this, TickerParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_LAST_ID,  "0"));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
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
        if(result instanceof TickerParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                if(!msg.isEmpty()){
                    showDialogNotif();
                    PreferenceManagers.clearByKey("notif", ActivityCarousel.this);
                }
            }
        }
    }

    private void showDialogNotif(){

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt_notif, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Notification");
        ImageView imgNotif = (ImageView) promptsView.findViewById(R.id.imgNotif);
        TextView txtNotif = (TextView) promptsView.findViewById(R.id.txtNotif);
        imgNotif.setVisibility(View.GONE);
        txtNotif.setVisibility(View.VISIBLE);
        String[] messages = msg.split("#");
        int code = Integer.parseInt(messages[0]);
        String info = "";
        switch (code){
            case 10:
                info = "Entry Ticket : "+ messages[1];
                break;
            case 20:
                info = "Unassigned Ticket : "+messages[1];
                break;
            case 31:
                info = "Status Update : "+ messages[1] +" "+messages[2];
                break;
            case 32:
                info = "Status Update : "+ messages[1] +" "+messages[2];
                break;
            case 33:
                info = "Status Update : "+ messages[1] +" "+messages[2];
                break;
            case 40:
                info = messages[1] +" : pengumuman";
                break;
            case 90:
                info = "Error : "+ messages[2];
                break;
        }
        txtNotif.setText(info);


        alertDialogBuilder
                .setCancelable(false)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //alertDialog.getWindow().getAttributes().windowAnimations = R.style.Animations_PopDownMenu_Right;
        // show it
        alertDialog.show();
    }
    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {
    }
}
